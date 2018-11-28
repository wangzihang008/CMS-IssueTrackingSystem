package com.fdmgroup.Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.fdmgroup.Entities.IssueDetail;
import com.fdmgroup.Services.IssueUpdateCustomerService;

public class IssueUpdateCustomerControllerTest {
	
	@Mock
	private IssueUpdateCustomerService iucs;
	@InjectMocks
	private IssueUpdateCustomerController iucc;
	
	@Before
	public void startInjectionMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void When_IssueUpdateCustomerController_Given_goToUpdateWithGETParameters_Then_returnCustomerDashboardJspPage() {
		Model model = mock(Model.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		String issueId = "1";
		String expected = "/dashboard/customer";
		
		when(iucs.check(issueId, model, req, session)).thenReturn(expected);
		
		String result = iucc.goToUpdate(model, req, session, issueId);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void When_IssueUpdateCustomerController_Given_goToUpdateWithPOSTParameters_Then_returnCustomerDashboardJspPage() {
		Model model = mock(Model.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		IssueDetail issueDetail = mock(IssueDetail.class);
		String issueId = "1";
		String expected = "/dashboard/customer";
		
		when(iucs.update(issueId, issueDetail, req, session)).thenReturn(expected);
		
		String result = iucc.goToUpdate(req, session, issueDetail, issueId);
		
		assertEquals(expected, result);
	}
}
