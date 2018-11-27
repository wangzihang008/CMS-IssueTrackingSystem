package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Calendar;

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
import com.fdmgroup.Entities.IssueDetail;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Status;

public class RequestForReassignControllerTest {

	@Mock
	private IssueDAO mockIssueDao;
	
	@Mock
	private UserDAO mockUserDao;

	@Mock
	private IssueDetailDAO mockIssueDetailDao;
	
	@InjectMocks
	private RequestForReassignController rc = new RequestForReassignController();

	@Before
	public void startInjectMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void when_requrestForRequest_then_returnRequestJsp() {
		
		String nextPage = rc.goToRequest();
		
		assertEquals(nextPage, "/issue/request");
	}
	
	@Test
	public void when_selectNothing_then_returnRequestJspAndDisplayMsg() {
		
		//arrange
		Model mockModel = mock(Model.class);
		HttpSession mockSession = mock(HttpSession.class);
		Issue mockIssue = mock(Issue.class);
		User mockUser = mock(User.class);
		when(mockSession.getAttribute("userName")).thenReturn("");
		when(mockUserDao.get("")).thenReturn(mockUser);
		int mockSelection = -1;
		
		//act
		String nextPage = rc.doRequest(mockModel, mockIssue, mockSelection, mockSession);
		
		//assert
		verify(mockModel).addAttribute("msg", "please select yes or no");
		assertEquals("/issue/request", nextPage);
	}
	
	@Test
	public void when_selectYes_then_returnDashboardDepadminJspAndChangeIssueStatusAndDisplayMsg() {
		
		//arrange
		Model mockModel = mock(Model.class);
		HttpSession mockSession = mock(HttpSession.class);
		Issue mockIssue = mock(Issue.class);
		User mockUser = mock(User.class);
		Department mockDepartment = mock(Department.class);
		when(mockSession.getAttribute("userName")).thenReturn("");
		when(mockUserDao.get("")).thenReturn(mockUser);
		when(mockUser.getUsername()).thenReturn("");
		when(mockUser.getDepartment()).thenReturn(mockDepartment);
		when(mockIssueDao.getAssignedIssuesByDepartment(mockDepartment)).thenReturn(null);
		when(mockIssue.toString()).thenReturn("");
		int mockSelection = 1;
		
		//act
		String nextPage = rc.doRequest(mockModel, mockIssue, mockSelection, mockSession);
		
		//assert
		verify(mockIssueDao).changeStatus(mockIssue, Status.REQUESTED);
		verify(mockIssueDetailDao).addIssueDetail(new IssueDetail(mockUser.getUsername() + " requested a reassignment for this issue", Calendar.getInstance(), mockUser, mockIssue));
		verify(mockModel).addAttribute("active_user", "");
		verify(mockModel).addAttribute("issues", null);
		verify(mockModel).addAttribute("msg", "Reassignment is requested for ");
		assertEquals("/dashboard/depadmin", nextPage);
	}
	
	@Test
	public void when_selectNo_then_returnDashboardDepadminJsp() {
		
		//arrange
		Model mockModel = mock(Model.class);
		HttpSession mockSession = mock(HttpSession.class);
		Issue mockIssue = mock(Issue.class);
		User mockUser = mock(User.class);
		Department mockDepartment = mock(Department.class);
		when(mockSession.getAttribute("userName")).thenReturn("");
		when(mockUserDao.get("")).thenReturn(mockUser);
		when(mockUser.getUsername()).thenReturn("");
		when(mockUser.getDepartment()).thenReturn(mockDepartment);
		when(mockIssueDao.getAssignedIssuesByDepartment(mockDepartment)).thenReturn(null);
		int mockSelection = 0;
		
		//act
		String nextPage = rc.doRequest(mockModel, mockIssue, mockSelection, mockSession);
		
		//assert
		verify(mockModel).addAttribute("active_user", "");
		verify(mockModel).addAttribute("issues", null);
		assertEquals("/dashboard/depadmin", nextPage);
	}
}
