package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.DAO.IssueDAO;

public class DashBoardDepartAdminControllerTest {
	
	@Mock
	private IssueDAO mockIssueDao;
	
	@Mock
	private IssueDAO mockUserDao;

	@Mock
	private IssueDAO mockIssueDetailDao;
	
	@InjectMocks
	private DashBoardDepartAdminController ddc = new DashBoardDepartAdminController();

	@Before
	public void startInjectMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void when_requrestForDashboardDepAdmin_then_returnDashboardDepAdminJsp() {
		
		String nextPage = ddc.goToDepartAdminDashBoard();
		
		assertEquals(nextPage, "dashboard/depadmin");
	}
	
	@Test
	public void when_selectNothing_then_returnDashboardDepAdminJspAndDisplayMsg() {
		
		
	}

	@Test
	public void when_selectIssue_then_returnIssueUpdateDepadminJspAndDisplayIssues() {
		
		
	}

}
