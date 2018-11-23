package com.fdmgroup.DAO;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

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

import com.fdmgroup.Entities.Issue;

public class IssueDAOTest {
	
	@Mock
	private EntityManagerFactory mockEmf;

	@Mock
	private EntityManager mockEm;

	@Mock
	private EntityTransaction mockEt;

	@InjectMocks
	private IssueDAO IssueDAO = new IssueDAO();

	@Before
	public void startInjectingMocks() {
		MockitoAnnotations.initMocks(this);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
	}

	@Test
	public void getEmfTest() {
		EntityManagerFactory injectedEmf = IssueDAO.getEmf();
		assertEquals(mockEmf, injectedEmf);
	}

	@Test
	public void adding_Issue_persists_and_cleans_up_resources() {
		Issue mockIssue = mock(Issue.class);
		IssueDAO.addIssue(mockIssue);
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockEm).persist(mockIssue);
		verify(mockEt).commit();
		verify(mockEm).close();
	}

	@Test
	public void getting_Issue_retrieves_Issue_and_cleans_up_resources() {
		IssueDAO.getIssue(100L);
		InOrder order = inOrder(mockEmf, mockEm);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).find(Issue.class, 100L);
		order.verify(mockEm).close();
	}
	
	@Test
	public void When_getIssuesByAdminId_Given_adminId_Then_returnAllIssuesAssignedForAdmin() {
		String str = "select i from Issue i WHERE i.admin=:admin";
		TypedQuery<Issue> query = mock(TypedQuery.class);
		ArrayList<Issue> mockResult = new ArrayList<Issue>();
		long adminId = 123;
		
		when(mockEm.createQuery(str)).thenReturn(query);
		when(query.getResultList()).thenReturn(mockResult);
		
		ArrayList<Issue> result = IssueDAO.getIssuesByAdminId(adminId);
		
		InOrder order = inOrder(mockEmf, mockEm, query);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).createQuery(str);
		order.verify(query).setParameter("admin", adminId);
		order.verify(query).getResultList();
		order.verify(mockEm).close();
		assertEquals(mockResult, result);
	}
}
