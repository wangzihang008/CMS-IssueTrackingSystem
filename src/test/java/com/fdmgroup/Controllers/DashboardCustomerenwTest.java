package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.ui.Model;

public class DashboardCustomerenwTest {

	@Test
	public void TestDeshBoardCustomer() {
		DashBoardCustomer db = new DashBoardCustomer();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("userName")).thenReturn(null);
		Model model = null;
		String url = db.gotoDashBoardCustomer(request, model);
		assertEquals(url, "index");
		
	}
}
