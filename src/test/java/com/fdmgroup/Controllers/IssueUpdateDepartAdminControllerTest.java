package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IssueUpdateDepartAdminControllerTest {

	@Test
	public void when_requrestForAdd_then_returnAddDetailDepartAdminJsp() {
		
		IssueUpdateDepartAdminController ic = new IssueUpdateDepartAdminController();
		String nextPage = ic.goToIssueUpdateDepartAdmin();
		
		assertEquals("/issue/update/depadmin", nextPage);
	}
}
