package com.fdmgroup.DAO;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.fdmgroup.Entities.IssueDetail;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Status;

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
		User admin = new User();
		
		when(mockEm.createQuery(str)).thenReturn(query);
		when(query.getResultList()).thenReturn(mockResult);
		
		ArrayList<Issue> result = IssueDAO.getIssuesByAdminId(admin);
		
		InOrder order = inOrder(mockEmf, mockEm, query);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).createQuery(str);
		order.verify(query).setParameter("admin", admin);
		order.verify(query).getResultList();
		order.verify(mockEm).close();
		assertEquals(mockResult, result);
	}

	@Test
	public void test_GettingIssuesByDepartment_thenReturn_CorrectIssues() {
		
		//arrange
		TypedQuery<Issue> mockQuery = mock(TypedQuery.class);
		Department mockDepartment = mock(Department.class);
		when(mockDepartment.getId()).thenReturn(0L);
		when(mockEm.createQuery("SELECT i FROM Issue i WHERE department_id = '" + mockDepartment.getId() + "' AND status = 1", Issue.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(null);
		
		//act
		List<Issue> issuesByDepartment = IssueDAO.getAssignedIssuesByDepartment(mockDepartment);
		
		//assert
		assertEquals(issuesByDepartment, null);
	}
	
	@Test
	public void When_IssueDAO_Given_updateWithIssue_Then_updateAndClearResorces() {
		Issue mockIssue = mock(Issue.class);
		Issue mockModifyIssue = mock(Issue.class);
		long issueId = 123;
		User user = mock(User.class);
		Department department = mock(Department.class);
		Calendar calendar = mock(Calendar.class);
		Status status = Status.ACTIVE;
		ArrayList<IssueDetail> details = new ArrayList<IssueDetail>();
		
		when(mockIssue.getId()).thenReturn(issueId); 
		when(mockEm.find(Issue.class, issueId)).thenReturn(mockModifyIssue);
		when(mockIssue.getAdmin()).thenReturn(user);
		when(mockIssue.getDepartment()).thenReturn(department);
		when(mockIssue.getLastUpdatedDate()).thenReturn(calendar);
		when(mockIssue.getStatus()).thenReturn(status);
		when(mockIssue.getDetails()).thenReturn(details);
		
		IssueDAO.update(mockIssue);
		
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockIssue, mockModifyIssue);
		order.verify(mockEm).getTransaction();
		order.verify(mockIssue).getId();
		order.verify(mockEm).find(Issue.class, mockIssue.getId());
		order.verify(mockEt).begin();
		order.verify(mockIssue).getAdmin();
		order.verify(mockModifyIssue).setAdmin(user);
		order.verify(mockIssue).getDepartment();
		order.verify(mockModifyIssue).setDepartment(department);
		order.verify(mockIssue).getLastUpdatedDate();
		order.verify(mockModifyIssue).setLastUpdatedDate(calendar);
		order.verify(mockIssue).getStatus();
		order.verify(mockModifyIssue).setStatus(status);
		order.verify(mockIssue).getDetails();
		order.verify(mockModifyIssue).setDetails(details);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
		
	}
	
	public void test_ChangingStatusOfAnIssue_then_DoChange() {
		
		//arrange
		Issue mockIssue = mock(Issue.class);
		Issue mockIssue2 = mock(Issue.class);
		Status mockStatus = Status.REJECTED;
		when(mockIssue.getId()).thenReturn(0L);
		when(mockEm.find(Issue.class, mockIssue.getId())).thenReturn(mockIssue2);
		
		//act
		IssueDAO.changeStatus(mockIssue, mockStatus);
		
		//assert
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockIssue2);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockIssue2).setStatus(mockStatus);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
}
