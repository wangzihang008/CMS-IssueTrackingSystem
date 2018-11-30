package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.Entities.Issue;

public class DashBoardCustomerTest {

	@Mock
	private IssueDAO issueDAO;

	@InjectMocks
	private DashBoardCustomer dbc = new DashBoardCustomer();

	@Before
	public void startInjectionMock() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void Given_DashboardCustomerController_When_goToDashboard_Then_returnDashboardCustomerJspPage() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("userId")).thenReturn(1L);
		when(issueDAO.getIssuesByUserId(1L)).thenReturn(new ArrayList<Issue>());
		Model model = mock(Model.class);
		String url = dbc.gotoDashBoardCustomer(request, model);
		assertEquals(url, "dashboard/customer");
	}
}