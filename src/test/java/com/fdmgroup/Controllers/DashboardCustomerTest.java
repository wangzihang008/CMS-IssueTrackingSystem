package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DashBoardCustomerTest {

	@Test
	public void GoToDashboard_return_dashBoardCustomer() {
		
		DashBoardCustomer dc = new DashBoardCustomer();
		String nextPage = dc.gotoDashBoardCustomer();
		
		assertEquals("dashBoardCustomer", nextPage);
	}
	
}
