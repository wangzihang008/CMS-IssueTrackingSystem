package com.fdmgroup.Services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import com.fdmgroup.DAO.IssueDAO;

public class AdminDashboardServiceTest {
	
	@Mock
	private IssueDAO issueDao;
	@InjectMocks
	private AdminDashboardService ads = new AdminDashboardService();
	
	@Before
	public void startInjectionMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void Given_AdminDashboardService_When_getAllIssues_Then_returnAllIssues() {
		ads.getAllIssues();
		verify(issueDao).getAllIssue();
	}
}
