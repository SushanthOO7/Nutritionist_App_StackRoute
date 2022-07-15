package com.project.model;


import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.project.model.Food;


public class FoodTest {
	private Food food;
	@Before
	public void setUp() throws Exception{
		food = new Food();
		food.setFdcId(502748);
		food.setDescription("PIE");
		food.setDataType("Branded");
		food.setPublicationDate("2019-04-01");
		food.setIngredients("PUMPKIN");
		food.setBrandOwner("Nestle USA Inc.");
		food.setScientificName(" ");
		food.setBrandedFoodCategory("Canned Vegetables");
	}
	
	@Test
	public void test() {
		new BeanTester().testBean(Food.class); 
	}
}