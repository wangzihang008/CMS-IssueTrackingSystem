package com.fdmgroup.Services;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.DAO.IssueDetailDAO;
import com.fdmgroup.Entities.Issue;

public class IssueDetailServiceTest {

	@Mock
	private IssueDetailDAO mockIssueDetailDAO;
	
	@Mock
	private Issue mockIssue;
	
	@InjectMocks
	private IssueDetailService ids = new IssueDetailService();
	
	@Before
	public void startInjectionMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void Given_IssueDetailService_When_displayDetails_Then_callGetIssueDetailsByIssue() {
		ids.displayAllDetails(mockIssue);
		verify(mockIssueDetailDAO).getIssueDetailsByIssue(mockIssue);
	}
}
