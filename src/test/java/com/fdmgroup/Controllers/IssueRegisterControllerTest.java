package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.IssueDetail;
import com.fdmgroup.Services.AdminDashboardService;
import com.fdmgroup.Services.IssueRegisterService;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class IssueRegisterControllerTest {

	@Mock
	private IssueRegisterService irs;
	@InjectMocks
	private IssueRegisterController irc = new IssueRegisterController();
	
	@Before
	public void startInjectionMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void Given_IssueRegisterController_When_goToIssueRegister_Then_returnIssueRegisterJspPage() {
		Model model = mock(Model.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		String nextPage = irc.goToIssueRegister(model, req);
		Issue issue = new Issue();
		
		InOrder order = inOrder(model, irs);
		order.verify(model).addAttribute("registerIssue", issue);
		order.verify(irs).getAllDepartments(req);
		assertEquals("issue/register", nextPage);
	}
	
	@Test
	public void When_IssueRegisterController_Given_goToIssueRegisterWithTitleContentDepartmentPriority_Then_returnIssueRegisterJspPage() {
		HttpSession session = mock(HttpSession.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		String title = "title";
		String content = "test";
		String department = "1";
		String priority = "1";
		String nextPage = irc.goToIssueRegister(title, content, department, priority, req, session);
		Issue issue = new Issue();
		
		when(irs.register(title, content, department, priority, req, session)).thenReturn("dashboard/customer");
		
		verify(irs).register(title, content, department, priority, req, session);
		assertEquals("dashboard/customer", nextPage);
	}
}
