
package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.IssueDetail;

public class ApproveControllerTest {
	
	@Mock
	private IssueDAO issueDAO;

	@InjectMocks
	ApproveController ac = new ApproveController();
	
	@Before
	public void startInjectMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void approveContollerTest() {
		Issue issue = mock(Issue.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		when(issueDAO .getIssue(123)).thenReturn(issue);
		List<IssueDetail> list = new ArrayList<IssueDetail>();
		when(issue.getDetails()).thenReturn(list);
		when(request.getSession()).thenReturn(session);
		String url = ac.toApprove(123, request);
		assertEquals(url, "issue/update/approve");
	}

	
}
