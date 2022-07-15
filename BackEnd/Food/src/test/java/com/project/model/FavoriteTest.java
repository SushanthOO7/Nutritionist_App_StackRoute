package com.project.model;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
public class FavoriteTest {
	private Favorite fav;
	List<Integer> List = new ArrayList<Integer>();
	
	@Before
	public void setUp() throws Exception {
		fav = new Favorite();
		fav.set_id("123");
		List.add(1002);
		List.add(1003);
		List.add(1004);
		fav.setFavoriteList(1002);
		fav.setMealList(1003);
	}
	@Test
	public void test() {
		new BeanTester().testBean(Nutrients.class);
	}
}