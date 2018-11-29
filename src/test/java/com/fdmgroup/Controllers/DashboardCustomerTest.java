package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.ui.Model;

public class DashboardCustomerTest {

	@Test
	public void TestDeshBoardCustomer() {
		DashBoardCustomer db = new DashBoardCustomer();
		
		HttpServletRequest request = null;
		Model model = null;
		
		String url = db.gotoDashBoardCustomer(request, model);
		assertEquals(url, "dashboard/customer");
		
		
	}
}
