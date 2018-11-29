package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdmgroup.DAO.DepartmentDAO;
import com.fdmgroup.Services.IssueReassignService;

public class IssueReassignControllerTest {

	@Mock
	private DepartmentDAO mockDepartmentDAO;
	
	@Mock
	private IssueReassignService mockIssueReassignService;
	
	@Mock
	private Model mockModel;
	
	@Mock
	private HttpServletRequest mockReq;
	
	@InjectMocks
	private IssueReassignController irc = new IssueReassignController();
	
	@Before
	public void startInjectionMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void Given_issueReassignController_When_goToIssueReassign_Then_returnReassignJspPage() {
		String nextPage = irc.goToIssueReassign(mockModel);
		assertEquals(nextPage, "issue/reassign");
	}
	
	@Test
	public void Given_issueReassignController_When_goToDashboard_Then_returnDashboardJspPage() {
		String nextPage = irc.goToDashboard(5, mockReq, mockModel);
		assertEquals(nextPage, "dashboard/admin");
	}
}
