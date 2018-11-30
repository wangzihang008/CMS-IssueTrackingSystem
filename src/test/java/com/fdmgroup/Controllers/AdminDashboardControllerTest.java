package com.fdmgroup.Controllers;

import org.springframework.ui.Model;

import com.fdmgroup.DAO.DepartmentDAO;
import com.fdmgroup.Entities.Department;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Enum.Type;
import com.fdmgroup.Services.AdminDashboardService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AdminDashboardControllerTest {

	@Mock
	private AdminDashboardService ads;

	@Mock
	private DepartmentDAO departmentDAO;

	@InjectMocks
	private AdminDashboardController adc = new AdminDashboardController();

	@Before
	public void startInjectionMock() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void When_AdminDashboardController_Given_goToAdminDashboardWithIssuesEmpty_Then_returnAdminDashboardJspPage() {
		Model model = mock(Model.class);
		HttpSession session = mock(HttpSession.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		ArrayList<Issue> issues = new ArrayList<Issue>();

		when(session.getAttribute("userType")).thenReturn(Type.ADMIN);
		when(ads.getAllIssues()).thenReturn(issues);

		String nextPage = adc.goToAdminDashboard(req, session, model);

		InOrder order = inOrder(ads, session);
		order.verify(session).getAttribute("userType");
		order.verify(ads).getAllIssues();
		assertEquals("dashboard/admin", nextPage);
	}

	@Test
	public void When_AdminDashboardController_Given_goToAdminDashboardWithIssuesNotEmpty_Then_returnAdminDashboardJspPage() {
		Model model = mock(Model.class);
		HttpSession session = mock(HttpSession.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		ArrayList<Issue> issues = new ArrayList<Issue>();
		ArrayList<Department> allDepartment = new ArrayList<Department>();
		Issue issue = mock(Issue.class);
		issues.add(issue);

		when(session.getAttribute("userType")).thenReturn(Type.ADMIN);
		when(ads.getAllIssues()).thenReturn(issues);
		when(departmentDAO.getAllDepartment()).thenReturn(allDepartment);

		String nextPage = adc.goToAdminDashboard(req, session, model);

		InOrder order = inOrder(ads, session, model);
		order.verify(session).getAttribute("userType");
		order.verify(ads).getAllIssues();
		order.verify(model).addAttribute("issues", issues);
		order.verify(model).addAttribute("allDepartment", allDepartment);
		assertEquals("dashboard/admin", nextPage);
	}

	@Test
	public void When_AdminDashboardController_Given_goToAdminDashboardWithNonadmin_Then_returnAdminDashboardJspPage() {
		Model model = mock(Model.class);
		HttpSession session = mock(HttpSession.class);
		HttpServletRequest req = mock(HttpServletRequest.class);

		when(session.getAttribute("userType")).thenReturn(Type.CUSTOMER);

		String nextPage = adc.goToAdminDashboard(req, session, model);

		verify(req).setAttribute("errorMessage", "You are not an admin user!");
		assertEquals("/index", nextPage);
	}
}
