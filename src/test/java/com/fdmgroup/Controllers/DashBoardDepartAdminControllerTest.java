package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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
import com.fdmgroup.Entities.Department;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.User;

public class DashBoardDepartAdminControllerTest {
	
	@Mock
	private IssueDAO mockIssueDao;
	
	@Mock
	private UserDAO mockUserDao;

	@Mock
	private IssueDetailDAO mockIssueDetailDao;
	
	@InjectMocks
	private DashBoardDepartAdminController ddc = new DashBoardDepartAdminController();

	@Before
	public void startInjectMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void when_requrestForDashboardDepAdmin_then_returnDashboardDepAdminJsp() {
		
		//arrange
		Model mockModel = mock(Model.class);
		HttpSession mockSession = mock(HttpSession.class);
		User mockUser = mock(User.class);
		Department mockDepartment = mock(Department.class);
		when(mockSession.getAttribute("userName")).thenReturn("");
		when(mockUserDao.get("")).thenReturn(mockUser);
		when(mockUser.getDepartment()).thenReturn(mockDepartment);
		when(mockUser.getUsername()).thenReturn("");
		when(mockIssueDao.getAssignedIssuesByDepartment(mockDepartment)).thenReturn(null);
		
		//act
		String nextPage = ddc.goToDepartAdminDashBoard(mockModel, mockSession);
		
		//assert
		verify(mockModel).addAttribute("active_user", "");
		verify(mockModel).addAttribute("issues", null);
		assertEquals(nextPage, "dashboard/depadmin");
	}
	
	@Test
	public void when_selectNothing_then_returnDashboardDepAdminJspAndDisplayMsg() {
		
		//arrange
		Model mockModel = mock(Model.class);
		Issue mockIssue = mock(Issue.class);
		HttpSession mockSession = mock(HttpSession.class);
		User mockUser = mock(User.class);
		Department mockDepartment = mock(Department.class);
		when(mockIssue.getTitle()).thenReturn("");
		when(mockSession.getAttribute("userName")).thenReturn("qz");
		when(mockUserDao.get("qz")).thenReturn(mockUser);
		when(mockUser.getUsername()).thenReturn("qz");
		when(mockUser.getDepartment()).thenReturn(mockDepartment);
		when(mockIssueDao.getAssignedIssuesByDepartment(mockDepartment)).thenReturn(null);
		
		//act
		String nextPage = ddc.checkIssues(mockModel, mockIssue, mockSession);
		
		//assert
		assertEquals(nextPage, "dashboard/depadmin");
		verify(mockModel).addAttribute("active_user", "qz");
		verify(mockModel).addAttribute("issues", null);
		verify(mockModel).addAttribute("select_msg", "please select an issue");
	}

	@Test
	public void when_selectIssue_then_returnIssueUpdateDepadminJspAndDisplayIssues() {
		
		//arrange
		Model mockModel = mock(Model.class);
		Issue mockIssue = mock(Issue.class);
		Issue mockIssue2 = mock(Issue.class);
		HttpSession mockSession = mock(HttpSession.class);
		when(mockIssue.getTitle()).thenReturn("Issue id: 23, aaaaaaa ");
		when(mockIssueDao.getIssue(23)).thenReturn(mockIssue2);
		when(mockIssueDetailDao.getIssueDetailsByIssue(mockIssue2)).thenReturn(null);
		
		//act
		String nextPage = ddc.checkIssues(mockModel, mockIssue, mockSession);
		
		//assert
		assertEquals(nextPage, "issue/update/depadmin");
		verify(mockModel).addAttribute("active_issue", mockIssue2);
		verify(mockModel).addAttribute("details", null);
	}

}
