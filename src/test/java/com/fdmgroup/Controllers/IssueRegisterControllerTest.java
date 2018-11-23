package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdmgroup.Entities.IssueDetail;
import com.fdmgroup.Services.AdminDashboardService;
import com.fdmgroup.Services.IssueRegisterService;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

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
		IssueDetail issueDetail = new IssueDetail();
		
		InOrder order = inOrder(model, irs);
		order.verify(model).addAttribute("newIssueDetail", issueDetail);
		order.verify(irs).getAllDepartments(req);
		assertEquals("issue/register", nextPage);
	}
}
