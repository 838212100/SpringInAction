package com.springinaction5_1.spittr.web;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HomeControllerTest {

	@Test
	public void testHome() {
		HomeController controller = new HomeController();
		assertEquals("hone", controller.home());
	}

}
