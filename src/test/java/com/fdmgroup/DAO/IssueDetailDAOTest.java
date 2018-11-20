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
}