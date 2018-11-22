package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdmgroup.Services.AdminDashboardService;
import com.fdmgroup.Services.IssueRegisterService;

import static org.mockito.Mockito.*;

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
		String nextPage = irc.goToIssueRegister(model);
		
		assertEquals("issue/register", nextPage);
	}
}
