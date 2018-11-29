package com.fdmgroup.Services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.DAO.DepartmentDAO;
import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.Department;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.User;

public class IssueReassignServiceTest {

	@Mock
	private IssueDAO mockIssueDAO;

	@Mock
	private UserDAO mockUserDAO;

	@Mock
	private DepartmentDAO mockDepartmentDAO;

	@Mock
	private Issue mockIssue;

	@Mock
	private Department mockDepartment;

	@Mock
	private ArrayList<User> mockAdmins;

	@InjectMocks
	private IssueReassignService irs = new IssueReassignService();

	@Before
	public void startInjectionMock() {
		MockitoAnnotations.initMocks(this);
		when(mockIssueDAO.getIssue(1)).thenReturn(mockIssue);
		when(mockDepartmentDAO.getDepartment("Support")).thenReturn(mockDepartment);
		when(mockUserDAO.getUserByDep(mockDepartment)).thenReturn(mockAdmins);
		when(mockAdmins.size()).thenReturn(10);
	}

	@Test
	public void Given_IssueReassignService_When_reassign_Then_callReassignHelper() {
		irs.reassign(1, "Support");
		Random rand = new Random();
		User returnedAdmin = mockAdmins.get(rand.nextInt(10));
		verify(mockIssueDAO).reassignHelper(mockIssue, mockDepartment, returnedAdmin);
	}
}
