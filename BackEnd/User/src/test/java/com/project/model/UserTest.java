package com.project.model;

import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.project.model.User;

public class UserTest {
	private User user;
	
	@Before
	public void setUp() throws Exception{
		user = new User();
		user.setUsername("hera");
		user.setPassword("password123");
		user.setEmail("hh@gmail.com");
		user.setName("Hera Hassan");
	}
	
	@Test
	public void test() {
		new BeanTester().testBean(User.class);
	}
}