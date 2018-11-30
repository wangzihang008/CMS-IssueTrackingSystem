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
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.IssueDetail;

public class IssueDetailDAOTest {

	@Mock
	private EntityManagerFactory mockEmf;

	@Mock
	private EntityManager mockEm;

	@Mock
	private EntityTransaction mockEt;

	@InjectMocks
	private IssueDetailDAO IssueDetailDAO = new IssueDetailDAO();

	@Before
	public void startInjectingMocks() {
		MockitoAnnotations.initMocks(this);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
	}

	@Test
	public void getEmfTest() {
		EntityManagerFactory injectedEmf = IssueDetailDAO.getEmf();
		assertEquals(mockEmf, injectedEmf);
	}

	@Test
	public void adding_IssueDetail_persists_and_cleans_up_resources() {
		IssueDetail mockIssueDetail = mock(IssueDetail.class);
		IssueDetailDAO.addIssueDetail(mockIssueDetail);
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockEm).persist(mockIssueDetail);
		verify(mockEt).commit();
		verify(mockEm).close();
	}

	@Test
	public void getting_IssueDetail_retrieves_IssueDetail_and_cleans_up_resources() {
		IssueDetailDAO.getIssueDetail(100L);
		InOrder order = inOrder(mockEmf, mockEm);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).find(IssueDetail.class, 100L);
		order.verify(mockEm).close();
	}
	
	@Test
	public void When_getIssueDetailsByIssue_Given_Issue_Then_returnListOfIssueDetails() {
		Issue issue = mock(Issue.class);
		long issueId = 123;
		when(issue.getId()).thenReturn(issueId);
		String strQuery = "SELECT d FROM IssueDetail d WHERE issue_id = '" + issue.getId() + "'";
		TypedQuery<IssueDetail> query = mock(TypedQuery.class);
		when(mockEm.createQuery(strQuery, IssueDetail.class)).thenReturn(query);
		ArrayList<IssueDetail> details = new ArrayList<IssueDetail>();
		when(query.getResultList()).thenReturn(details);
		
		IssueDetailDAO.getIssueDetailsByIssue(issue);
		
		InOrder order = inOrder(mockEmf, mockEm, issue, query);
		order.verify(mockEmf).createEntityManager();
		order.verify(issue).getId();
		order.verify(mockEm).createQuery(strQuery, IssueDetail.class);
		order.verify(query).getResultList();
		order.verify(mockEm).close();
	}
}
