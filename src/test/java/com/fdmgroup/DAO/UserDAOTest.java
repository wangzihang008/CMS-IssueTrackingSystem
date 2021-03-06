package com.fdmgroup.DAO;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.Entities.Department;
import com.fdmgroup.Entities.User;

public class UserDAOTest {

	@Mock
	private EntityManagerFactory mockEmf;

	@Mock
	private EntityManager mockEm;

	@Mock
	private EntityTransaction mockEt;

	@InjectMocks
	private UserDAO userDAO = new UserDAO();

	@Before
	public void startInjectingMocks() {
		MockitoAnnotations.initMocks(this);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
	}

	@Test
	public void getEmfTest() {
		EntityManagerFactory injectedEmf = userDAO.getEmf();
		assertEquals(mockEmf, injectedEmf);
	}

	@Test
	public void adding_User_persists_and_cleans_up_resources() {
		User mockUser = mock(User.class);
		userDAO.addUser(mockUser);
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockEm).persist(mockUser);
		verify(mockEt).commit();
		verify(mockEm).close();
	}

	@Test
	public void getting_User_retrieves_User_and_cleans_up_resources() {
		userDAO.getUser(100L);
		InOrder order = inOrder(mockEmf, mockEm);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).find(User.class, 100L);
		order.verify(mockEm).close();
	}
	
	@Test
	public void When_getUserByDep_Given_depId_Then_returnListOfUser() {
		TypedQuery<User> query = mock(TypedQuery.class);
		ArrayList<User> mockResult = new ArrayList<User>();
		String str = "select u from User u where u.department=:department";
		Department department = new Department();
		
		when(mockEm.createQuery(str)).thenReturn(query);
		when(query.getResultList()).thenReturn(mockResult);
		
		ArrayList<User> result = userDAO.getUserByDep(department);
		
		InOrder order = inOrder(mockEm, query);
		order.verify(mockEm).createQuery(str);
		order.verify(query).setParameter("department", department);
		order.verify(query).getResultList();
		order.verify(mockEm).close();
		assertEquals(result, mockResult);
	}
	
	@Test
	public void get_user_by_usernmae_given_user_exists_return_user() {
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
//		List<User> mockList = mock(List.class);
		List<User> mockList = new ArrayList<User>();
		User mockUser = mock(User.class);
		mockList.add(mockUser);
		TypedQuery<User> mockQuery = mock(TypedQuery.class);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("SELECT u FROM User AS u WHERE u.username=:username", User.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mockList);
//		when(mockList.get(0)).thenReturn(mockUser);
		User user = userDAO.get("abc");
		
		
		
		verify(mockEmf).createEntityManager();
		verify(mockQuery).setParameter("username", "abc");
		verify(mockQuery).getResultList();
		verify(mockEm).close();
		assertEquals(mockUser, user);
		
		
	}
	@Test
	public void get_user_by_usernmae_given_user_does_not_exists_return_user() {
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
//		List<User> mockList = mock(List.class);
		List<User> mockList = new ArrayList<User>();
//		User mockUser = null;
//		mockList.add(mockUser);
		TypedQuery<User> mockQuery = mock(TypedQuery.class);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("SELECT u FROM User AS u WHERE u.username=:username", User.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mockList);
//		when(mockList.get(0)).thenReturn(mockUser);
		User user = userDAO.get("abc");
		
		
		
		verify(mockEmf).createEntityManager();
		verify(mockQuery).setParameter("username", "abc");
		verify(mockQuery).getResultList();
		verify(mockEm).close();
		assertEquals(null, user);
		
		
	}
}
