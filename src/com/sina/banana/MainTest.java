package com.sina.banana;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainTest {
	
	private Main main;

	@Before
	public void setUp() throws Exception {
		main = new Main();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testMain() {
		String[] args = {"normal"};
		main.main(args);
	}

}
