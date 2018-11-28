package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.DAO.IssueDetailDAO;
import com.fdmgroup.Controllers.IssueDetailController;

public class IssueDetailControllerTest {

	@Mock
	private IssueDetailDAO mockIssueDetailDAO;
	
	@Mock
	private IssueDAO mockIssueDAO;
	
	@Mock
	private Model mockModel;
	
	@InjectMocks
	private IssueDetailController idc = new IssueDetailController();
	
	@Before
	public void startInjectionMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void Given_issueDetailController_When_goToIssueDetail_Then_returnDetailJspPage() {
		String nextPage = idc.goToIssueDetail(2, mockModel);
		assertEquals(nextPage, "issue/detail");
	}
}
