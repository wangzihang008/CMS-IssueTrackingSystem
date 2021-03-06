package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.DAO.IssueDetailDAO;
import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.User;

public class IssueApprovedControllerTest {

	@Mock
	private IssueDAO issueDAO;
	
	@Mock
	private UserDAO userDAO;
	
	@Mock
	private IssueDetailDAO issueDetailDAO;
	
	@Mock
	private User user;

	@Mock
	private Model mockModel;

	@InjectMocks
	IssueApprovedController iac = new IssueApprovedController();

	@Before
	public void startInjectMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void Given_IssueApprovedController_When_approve_Then_returnDashboardCustomerJspPage() {
		Issue issue = mock(Issue.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);

		when(issueDAO.getIssue(12312121)).thenReturn(issue);
		when(session.getAttribute("userName")).thenReturn("name");
		when(userDAO.get("name")).thenReturn(user);
		List<Issue> list = new ArrayList<Issue>();
		when(user.getIssues()).thenReturn(list);
		String url = iac.approve(12312121, request, mockModel);
		assertEquals(url, "dashboard/customer");

	}
}
