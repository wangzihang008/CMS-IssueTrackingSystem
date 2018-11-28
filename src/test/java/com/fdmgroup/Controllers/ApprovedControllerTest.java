package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

public class ApprovedControllerTest {

	@Test
	public void IssueApprovedController() {
		
		IssueApprovedController iac = new IssueApprovedController();
		HttpServletRequest request=null;
		String url= iac.approve("12312121", request);
		assertEquals(url, "dashboard/customer");
		
		
		
	}
}
