package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IndexControllerTest {

	@Test
	public void Given_indexController_When_goToIndex_Then_returnIndexJspPage() {
		IndexController ic = new IndexController();
		String nextPage = ic.goToIndex();

		assertEquals("index", nextPage);
	}
}
