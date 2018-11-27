package com.fdmgroup.Controllers;

import org.springframework.ui.Model;

import com.fdmgroup.Services.AdminDashboardService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AdminDashboardControllerTest {

	@Mock
	private AdminDashboardService ads;
	@InjectMocks
	private AdminDashboardController adc = new AdminDashboardController();

	@Before
	public void startInjectionMock() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void Given_AdminDashboardController_When_goToAdminDashboard_Then_returnAdminDashboardJspPage() {
		Model model = mock(Model.class);
		HttpSession session = mock(HttpSession.class);
		String nextPage = adc.goToAdminDashboard(session, model);

		verify(ads).getAllIssues();
		assertEquals("dashboard/admin", nextPage);
	}
}
