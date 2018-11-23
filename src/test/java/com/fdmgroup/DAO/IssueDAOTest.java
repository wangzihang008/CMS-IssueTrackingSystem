package com.fdmgroup.DAO;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
	public void test_GettingIssuesByDepartment_thenReturn_CorrectIssues() {
		
		//arrange
		//Issue mockIssue = mock(Issue.class);
		TypedQuery<Issue> mockQuery = mock(TypedQuery.class);
		Department mockDepartment = mock(Department.class);
		when(mockDepartment.getId()).thenReturn(21L);
		when(mockEm.createQuery("SELECT i FROM Issue i WHERE department_id = '" + mockDepartment.getId() + "'", Issue.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(null);
		
		//act
		List<Issue> issuesByDepartment = IssueDAO.getIssuesByDepartment(mockDepartment);
		
		//assert
		assertEquals(issuesByDepartment, null);
	}
}
