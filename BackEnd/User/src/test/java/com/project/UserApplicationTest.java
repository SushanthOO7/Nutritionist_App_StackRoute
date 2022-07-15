package com.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

@SpringBootTest
@PropertySource("classpath:dbconfig.properties")
class NutritionistApplicationTests {

	@Test
	void contextLoads() {
	}

}
