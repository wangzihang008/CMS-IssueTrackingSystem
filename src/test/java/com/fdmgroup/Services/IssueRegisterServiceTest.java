package com.fdmgroup.Services;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;

import com.fdmgroup.DAO.DepartmentDAO;
import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.DAO.IssueDetailDAO;
import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.Department;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.IssueDetail;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Status;

public class IssueRegisterServiceTest {
	@Mock
	private IssueDAO issueDao;
	@Mock
	private IssueDetailDAO issueDetailDao;
	@Mock
	private UserDAO userDao;
	@Mock
	private DepartmentDAO departmentDao;
	@Mock
	private Issue issue;
	@Mock
	private IssueDetail issueDetail;
	@Mock
	private Calendar calendar;
	@InjectMocks
	private IssueRegisterService irs;
	
	@Before
	public void startInjectionMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void When_IssueRegisterService_Given_registerWithEmptyContent_Then_returnErrorMessageAndIssueRegisterJspPage() {
		String str = "";
		String message = "Content of issue is missing!";
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		
		String result = irs.register(str, str, str, str, req, session);
		
		verify(req).setAttribute("errorMessage", message);
		assertEquals("issue/register", result);
	}
	
	@Test
	public void When_IssueRegisterService_Given_registerWithEmptyTitle_Then_returnErrorMessageAndIssueRegisterJspPage() {
		String str = "";
		String strNonempty = "XXX";
		String message = "Title of issue is missing!";
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		
		String result = irs.register(str, strNonempty, str, str, req, session);
		
		verify(req).setAttribute("errorMessage", message);
		assertEquals("issue/register", result);
	}
	
	@Test
	public void When_IssueRegisterService_Given_registerWithEmptyDepartment_Then_returnErrorMessageAndIssueRegisterJspPage() {
		String str = "";
		String strNonempty = "XXX";
		String message = "Department of issue is missing!";
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		
		String result = irs.register(strNonempty, strNonempty, str, str, req, session);
		
		verify(req).setAttribute("errorMessage", message);
		assertEquals("issue/register", result);
	}
	
	@Test
	public void When_IssueRegisterService_Given_registerWithEmptyPriority_Then_returnErrorMessageAndIssueRegisterJspPage() {
		String str = "";
		String strNonempty = "XXX";
		String message = "Priority of issue is missing!";
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		
		String result = irs.register(strNonempty, strNonempty, strNonempty, str, req, session);
		
		verify(req).setAttribute("errorMessage", message);
		assertEquals("issue/register", result);
	}
	
	@Test
	public void When_IssueRegisterService_Given_registerWithNoAdminInThatDepartment_Then_returnDashboardPageAndRegisterIssueIntoDatabase() {
		String strNonempty = "XXX";
		String strNum = "1";
		String message = "Issue created successful";
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		Department department = mock(Department.class);
		ArrayList<User> depAdmins = new ArrayList<User>();
		User user = mock(User.class);
		long userId = 123;
		
		
		when(departmentDao.getDepartment(Long.parseLong(strNum))).thenReturn(department);
		when(userDao.getUserByDep(department)).thenReturn(depAdmins);
		when(session.getAttribute("userId")).thenReturn(userId);
		when(userDao.getUser(userId)).thenReturn(user);
		
		String result = irs.register(strNonempty, strNonempty, strNum, strNum, req, session);
		
		InOrder order = inOrder(issueDao, issue, issueDetailDao, issueDetail, departmentDao, calendar, userDao, session, req);
		
		order.verify(issue).setTitle(strNonempty);
		order.verify(issue).setPriority(Integer.parseInt(strNum));
		order.verify(issueDetail).setContent(strNonempty);
		order.verify(departmentDao).getDepartment(Long.parseLong(strNum));
		order.verify(userDao).getUserByDep(department);
		order.verify(session).getAttribute("userId");
		order.verify(userDao).getUser((long)userId);
		order.verify(issue).setCreateDate(calendar);
		order.verify(issue).setCreateUser(user);
		order.verify(issue).setDepartment(department);
		order.verify(issue).setLastUpdatedDate(calendar);
		order.verify(issue).setStatus(Status.ASSIGNED);
		order.verify(issueDetail).setCreateDate(calendar);
		order.verify(issueDetail).setUser(user);
		order.verify(issueDetail).setStatus(Status.ASSIGNED);
		order.verify(issue).addDetail(issueDetail);
		order.verify(issueDao).addIssue(issue);
		order.verify(req).setAttribute("message", "Issue created successful");
		assertEquals("dashboard/customer", result);
	}
	
	@Test
	public void When_IssueRegisterService_Given_register_Then_returnDashboardPageAndRegisterIssueIntoDatabase() {
		String strNonempty = "XXX";
		String strNum = "1";
		String message = "Issue created successful";
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		Department department = mock(Department.class);
		ArrayList<User> depAdmins = new ArrayList<User>();
		User user = mock(User.class);
		User admin = mock(User.class);
		depAdmins.add(admin);
		long userId = 123;
		
		
		when(departmentDao.getDepartment(Long.parseLong(strNum))).thenReturn(department);
		when(userDao.getUserByDep(department)).thenReturn(depAdmins);
		when(session.getAttribute("userId")).thenReturn(userId);
		when(userDao.getUser(userId)).thenReturn(user);
		
		String result = irs.register(strNonempty, strNonempty, strNum, strNum, req, session);
		
		InOrder order = inOrder(issueDao, issue, issueDetailDao, issueDetail, departmentDao, calendar, userDao, session, req);
		
		order.verify(issue).setTitle(strNonempty);
		order.verify(issue).setPriority(Integer.parseInt(strNum));
		order.verify(issueDetail).setContent(strNonempty);
		order.verify(departmentDao).getDepartment(Long.parseLong(strNum));
		order.verify(userDao).getUserByDep(department);
		order.verify(session).getAttribute("userId");
		order.verify(userDao).getUser((long)userId);
		order.verify(issue).setCreateDate(calendar);
		order.verify(issue).setCreateUser(user);
		order.verify(issue).setDepartment(department);
		order.verify(issue).setLastUpdatedDate(calendar);
		order.verify(issue).setStatus(Status.ASSIGNED);
		order.verify(issueDetail).setCreateDate(calendar);
		order.verify(issueDetail).setUser(user);
		order.verify(issueDetail).setStatus(Status.ASSIGNED);
		order.verify(issue).addDetail(issueDetail);
		order.verify(issue).setAdmin(admin);
		order.verify(issueDao).addIssue(issue);
		order.verify(req).setAttribute("message", "Issue created successful");
		assertEquals("dashboard/customer", result);
	}
	
	@Test
	public void When_IssueRegisterService_getAllDepartments_Then_returnAllDepartment() {
		HttpServletRequest req = mock(HttpServletRequest.class);
		ArrayList<Department> mockList = new ArrayList<Department>();
		
		when(departmentDao.getAllDepartment()).thenReturn(mockList);
		
		irs.getAllDepartments(req);
		
		InOrder order = inOrder(departmentDao, req);
		order.verify(departmentDao).getAllDepartment();
		order.verify(req).setAttribute("allDepartment", mockList);
	}
}
