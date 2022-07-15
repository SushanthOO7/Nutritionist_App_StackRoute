package com.project.model;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
public class NutrientsTest {
	private Nutrients nutrients;
	
	@Before
	public void setUp() throws Exception {
		nutrients = new Nutrients();
		nutrients.setId(123);
		nutrients.setName("Protein");
		nutrients.setUnitName("g");
		nutrients.setAmount("2.0");
		nutrients.setDescription("Calculated");
	}
	@Test
	public void test() {
		new BeanTester().testBean(Nutrients.class);
	}

}