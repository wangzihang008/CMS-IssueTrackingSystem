package com.fdmgroup.DAO;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.Entities.Department;

public class DepartmentDAOTest {

	@Mock
	private EntityManagerFactory mockEmf;

	@Mock
	private EntityManager mockEm;

	@Mock
	private EntityTransaction mockEt;

	@InjectMocks
	private DepartmentDAO DepartmentDAO = new DepartmentDAO();

	@Before
	public void startInjectingMocks() {
		MockitoAnnotations.initMocks(this);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
	}

	@Test
	public void getEmfTest() {
		EntityManagerFactory injectedEmf = DepartmentDAO.getEmf();
		assertEquals(mockEmf, injectedEmf);
	}
	
	@Test
	public void adding_Department_persists_and_cleans_up_resources() {
		Department mockDepartment = mock(Department.class);
		DepartmentDAO.addDepartment(mockDepartment);
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockEm).persist(mockDepartment);
		verify(mockEt).commit();
		verify(mockEm).close();
	}

	@Test
	public void getting_Department_retrieves_Department_and_cleans_up_resources() {
		DepartmentDAO.getDepartment(100L);
		InOrder order = inOrder(mockEmf, mockEm);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).find(Department.class, 100L);
		order.verify(mockEm).close();
	}
}
