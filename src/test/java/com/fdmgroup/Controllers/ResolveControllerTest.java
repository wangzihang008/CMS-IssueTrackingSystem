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

public class ResolveControllerTest {

	@Mock
	private IssueDAO mockIssueDao;
	
	@Mock
	private UserDAO mockUserDao;

	@Mock
	private IssueDetailDAO mockIssueDetailDao;
	
	@Mock
	private Calendar calendar;
	
	@InjectMocks
	private ResolveController rc = new ResolveController();

	@Before
	public void startInjectMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void when_requrestForReject_then_returnRejectJsp() {
		
		String nextPage = rc.goToResolve();
		
		assertEquals(nextPage, "/issue/resolve");
	}
	
	@Test
	public void when_selectNothing_then_returnResolveJspAndDisplayMsg() {
		
		//arrange
		Model mockModel = mock(Model.class);
		HttpSession mockSession = mock(HttpSession.class);
		Issue mockIssue = mock(Issue.class);
		User mockUser = mock(User.class);
		when(mockSession.getAttribute("userName")).thenReturn("");
		when(mockUserDao.get("")).thenReturn(mockUser);
		int mockSelection = -1;
		
		//act
		String nextPage = rc.markResolve(mockModel, mockIssue, mockSelection, mockSession);
		
		//assert
		verify(mockModel).addAttribute("msg", "please select yes or no");
		assertEquals("/issue/resolve", nextPage);
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
		String nextPage = rc.markResolve(mockModel, mockIssue, mockSelection, mockSession);
		
		//assert
		verify(mockIssueDao).changeStatus(mockIssue, Status.RESOLVED);
		verify(mockIssueDetailDao).addIssueDetail(new IssueDetail(" marked this issue resolved", calendar, mockUser, mockIssue));
		verify(mockModel).addAttribute("active_user", "");
		verify(mockModel).addAttribute("issues", null);
		verify(mockModel).addAttribute("msg", "You have successfully marked  resolved");
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
		String nextPage = rc.markResolve(mockModel, mockIssue, mockSelection, mockSession);
		
		//assert
		verify(mockModel).addAttribute("active_user", "");
		verify(mockModel).addAttribute("issues", null);
		assertEquals("/dashboard/depadmin", nextPage);
	}
}
