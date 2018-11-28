package com.fdmgroup.Services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.DAO.IssueDetailDAO;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.IssueDetail;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Status;
import com.fdmgroup.Enum.Type;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Calendar;

public class IssueUpdateCustomerServiceTest {
	
	@Mock
	private IssueDAO issueDao;
	@Mock
	private Calendar calendar;
	@Mock
	private IssueDetailDAO issueDetailDao;
	@Mock
	private IssueDetail issueDetail;
	@InjectMocks
	private IssueUpdateCustomerService iucs;
	
	@Before
	public void startInjectionMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void When_check_Given_SessionWithNullUserType_Then_returnIndexJspPage() {
		String issueId = "1";
		Model model = mock(Model.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		
		when(session.getAttribute("userType")).thenReturn(null);
		
		String result = iucs.check(issueId, model, req, session);
		
		InOrder order = inOrder(req);
		order.verify(req).setAttribute("errorMessage", "You have not logged in. Please login first.");
		assertEquals("/index", result);
	}
	
	@Test
	public void When_check_Given_SessionWithNoThatIssueId_Then_returnDashboardJspPage() {
		String issueId = "1";
		Model model = mock(Model.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		
		when(session.getAttribute("userType")).thenReturn(Type.CUSTOMER);
		when(issueDao.getIssue(Long.parseLong(issueId))).thenReturn(null);
		
		String result = iucs.check(issueId, model, req, session);
		
		InOrder order = inOrder(req, issueDao);
		order.verify(issueDao).getIssue(Long.parseLong(issueId));
		order.verify(req).setAttribute("errorMessage", "The issue does not exist!");
		assertEquals("/dashboard/customer", result);
	}
	
	@Test
	public void When_check_Given_SessionWithCreateUserIsNotLogedInUser_Then_returnDashboardJspPage() {
		String issueId = "1";
		Model model = mock(Model.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		Issue issue = mock(Issue.class);
		User user = mock(User.class);
		long createUserId = 1;
		long logedInUserId = 2;
		
		when(session.getAttribute("userType")).thenReturn(Type.CUSTOMER);
		when(issueDao.getIssue(Long.parseLong(issueId))).thenReturn(issue);
		when(session.getAttribute("userId")).thenReturn(logedInUserId);
		when(issue.getCreateUser()).thenReturn(user);
		when(user.getId()).thenReturn(createUserId);
		
		String result = iucs.check(issueId, model, req, session);
		
		InOrder order = inOrder(req, issueDao, session, issue, user);
		order.verify(issueDao).getIssue(Long.parseLong(issueId));
		order.verify(session).getAttribute("userId");
		order.verify(issue).getCreateUser();
		order.verify(user).getId();
		order.verify(req).setAttribute("errorMessage", "You have no right to update this issue");
		
		assertEquals("/dashboard/customer", result);
	}
	
	@Test
	public void When_check_Given_valaidParameters_Then_returnDashboardJspPage() {
		String issueId = "1";
		Model model = mock(Model.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		Issue issue = mock(Issue.class);
		User user = mock(User.class);
		long createUserId = 1;
		long logedInUserId = 1;
		
		when(session.getAttribute("userType")).thenReturn(Type.CUSTOMER);
		when(issueDao.getIssue(Long.parseLong(issueId))).thenReturn(issue);
		when(session.getAttribute("userId")).thenReturn(logedInUserId);
		when(issue.getCreateUser()).thenReturn(user);
		when(user.getId()).thenReturn(createUserId);
		
		String result = iucs.check(issueId, model, req, session);
		
		InOrder order = inOrder(req, issueDao, session, issue, user, model);
		order.verify(issueDao).getIssue(Long.parseLong(issueId));
		order.verify(session).getAttribute("userId");
		order.verify(issue).getCreateUser();
		order.verify(user).getId();
		order.verify(model).addAttribute("newIssueDetail", issueDetail);
		order.verify(req).setAttribute("issueId", issueId);
		
		assertEquals("/issue/update/customer", result);
	}
	
	@Test
	public void When_update_Given_SessionWithNullUserType_Then_returnIndexJspPage() {
		String issueId = "1";
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		
		when(session.getAttribute("userType")).thenReturn(null);
		
		String result = iucs.update(issueId, issueDetail, req, session);
		
		InOrder order = inOrder(req);
		order.verify(req).setAttribute("errorMessage", "You have not logged in. Please login first.");
		assertEquals("/index", result);
	}
	
	@Test
	public void When_update_Given_SessionWithNoThatIssueId_Then_returnDashboardJspPage() {
		String issueId = "1";
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		
		when(session.getAttribute("userType")).thenReturn(Type.CUSTOMER);
		when(issueDao.getIssue(Long.parseLong(issueId))).thenReturn(null);
		
		String result = iucs.update(issueId, issueDetail, req, session);
		
		InOrder order = inOrder(req, issueDao);
		order.verify(issueDao).getIssue(Long.parseLong(issueId));
		order.verify(req).setAttribute("errorMessage", "The issue does not exist!");
		assertEquals("/dashboard/customer", result);
	}
	
	@Test
	public void When_update_Given_SessionWithCreateUserIsNotLogedInUser_Then_returnDashboardJspPage() {
		String issueId = "1";
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		Issue issue = mock(Issue.class);
		User user = mock(User.class);
		long createUserId = 1;
		long logedInUserId = 2;
		
		when(session.getAttribute("userType")).thenReturn(Type.CUSTOMER);
		when(issueDao.getIssue(Long.parseLong(issueId))).thenReturn(issue);
		when(session.getAttribute("userId")).thenReturn(logedInUserId);
		when(issue.getCreateUser()).thenReturn(user);
		when(user.getId()).thenReturn(createUserId);
		
		String result = iucs.update(issueId, issueDetail, req, session);
		
		InOrder order = inOrder(req, issueDao, session, issue, user);
		order.verify(issueDao).getIssue(Long.parseLong(issueId));
		order.verify(session).getAttribute("userId");
		order.verify(issue).getCreateUser();
		order.verify(user).getId();
		order.verify(req).setAttribute("errorMessage", "You have no right to update this issue");
		
		assertEquals("/dashboard/customer", result);
	}
	
	@Test
	public void When_update_Given_valaidParameters_Then_returnDashboardJspPage() {
		String issueId = "1";
		Model model = mock(Model.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		Issue issue = mock(Issue.class);
		User user = mock(User.class);
		long createUserId = 1;
		long logedInUserId = 1;
		
		when(session.getAttribute("userType")).thenReturn(Type.CUSTOMER);
		when(issueDao.getIssue(Long.parseLong(issueId))).thenReturn(issue);
		when(session.getAttribute("userId")).thenReturn(logedInUserId);
		when(issue.getCreateUser()).thenReturn(user);
		when(user.getId()).thenReturn(createUserId);
		
		String result = iucs.update(issueId, issueDetail, req, session);
		
		InOrder order = inOrder(req, issueDao, session, issue, user, model, issueDetail, issueDetailDao);
		order.verify(issueDao).getIssue(Long.parseLong(issueId));
		order.verify(session).getAttribute("userId");
		order.verify(issue).getCreateUser();
		order.verify(user).getId();
		order.verify(issueDetail).setCreateDate(calendar);
		order.verify(issueDetail).setIssue(issue);
		order.verify(issueDetail).setStatus(Status.CUSTOMERUPDATE);
		order.verify(issue).getCreateUser();
		order.verify(issueDetail).setUser(issue.getCreateUser());
		order.verify(issueDetailDao).addIssueDetail(issueDetail);
		
		assertEquals("/dashboard/customer", result);
	}
}
