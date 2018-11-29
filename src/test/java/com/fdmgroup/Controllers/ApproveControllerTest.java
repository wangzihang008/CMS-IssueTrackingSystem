
package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.ui.Model;

public class ApproveControllerTest {

	
	@Test
	public void approveContollerTest() {
		ApproveController ac = new ApproveController();

		HttpServletRequest request = null;

		String url = ac.toApprove("123", request);
		assertEquals(url, "issue/update/approve");
	}

	
}
