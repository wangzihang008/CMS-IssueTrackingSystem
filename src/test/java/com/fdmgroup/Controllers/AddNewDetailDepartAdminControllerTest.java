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

public class AddNewDetailDepartAdminControllerTest {

	@Mock
	private IssueDAO mockIssueDao;
	
	@Mock
	private UserDAO mockUserDao;

	@Mock
	private IssueDetailDAO mockIssueDetailDao;
	
	@InjectMocks
	private AddNewDetailDepartAdminController ac = new AddNewDetailDepartAdminController();

	@Before
	public void startInjectMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void when_requrestForAdd_then_returnAddDetailDepartAdminJsp() {
		
		//arrange
		Model mockModel = mock(Model.class);
		
		//act
		String nextPage = ac.goToAddDetail(mockModel);
		
		//assert
		verify(mockModel).addAttribute("blank_detail", new IssueDetail());
		assertEquals(nextPage, "/issue/addDetailDepartAdmin");
	}
	
	@Test
	public void when_enterAndSubmitDetail_then_returnDashboardDepadminJspAndDisplayMsg() {
		
		//arrange
		Model mockModel = mock(Model.class);
		HttpSession mockSession = mock(HttpSession.class);
		Issue mockIssue = mock(Issue.class);
		IssueDetail mockIssueDetail = mock(IssueDetail.class);
		User mockUser = mock(User.class);
		Department mockDepartment = mock(Department.class);
		when(mockSession.getAttribute("userName")).thenReturn("");
		when(mockUserDao.get("")).thenReturn(mockUser);
		when(mockUser.getUsername()).thenReturn("");
		when(mockUser.getDepartment()).thenReturn(mockDepartment);
		when(mockIssueDao.getAssignedIssuesByDepartment(mockDepartment)).thenReturn(null);
		when(mockIssue.toString()).thenReturn("");
		
		//act
		String nextPage = ac.addDetail(mockModel, mockIssue, mockIssueDetail, mockSession);
		
		//assert
		verify(mockModel).addAttribute("blank_detail", mockIssueDetail);
		verify(mockIssueDetail).setUser(mockUser);
		verify(mockIssueDetail).setIssue(mockIssue);
		verify(mockIssueDetail).setCreateDate(Calendar.getInstance());
		verify(mockIssueDetailDao).addIssueDetail(mockIssueDetail);
		verify(mockModel).addAttribute("active_user", "");
		verify(mockModel).addAttribute("issues", null);
		verify(mockModel).addAttribute("msg", "You just added a new detail to ");
		assertEquals("/dashboard/depadmin", nextPage);
	}
}
