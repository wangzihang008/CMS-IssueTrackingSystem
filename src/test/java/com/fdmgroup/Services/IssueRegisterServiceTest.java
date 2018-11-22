package com.fdmgroup.Services;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import com.fdmgroup.DAO.DepartmentDAO;
import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.DAO.IssueDetailDAO;
import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.Department;

public class IssueRegisterServiceTest {
	@Mock
	private IssueDAO issueDao;
	@Mock
	private IssueDetailDAO issueDetailDao;
	@Mock
	private UserDAO userDao;
	@Mock
	private DepartmentDAO departmentDao;
	@InjectMocks
	private IssueRegisterService irs;
	
	@Before
	public void startInjectionMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void Given_IssueRegisterService_When_register_Then_registerNewIssuesIntoDatabase() {
		
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
