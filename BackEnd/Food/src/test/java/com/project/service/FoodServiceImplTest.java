package com.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.project.model.Food;
import com.project.model.Nutrients;
import com.project.repository.FoodRepository;

@RunWith(MockitoJUnitRunner.class)
public class FoodServiceImplTest {
	
	@Mock
	private FoodRepository foodRepo;
	
	@InjectMocks
	private FoodServiceImpl foodServiceImpl;
	
	Food food;
	List<Nutrients> nutrientsList;
	Nutrients nutrients;
	Optional<Food> optFood;
	List<Food> allFood;
	
	@Before
	public void setup() throws Exception {
		nutrientsList = new ArrayList<Nutrients>();
		nutrients = new Nutrients(111,"Water","86.2","g","");
		nutrientsList.add(nutrients);
		nutrients = new Nutrients(112,"Energy","51","kcal","Calculated");
		nutrientsList.add(nutrients);
		food = new Food(1002,"Apple","Branded","2019-12-06","","Brand123","Malus domestica","Fruit",nutrientsList);
		optFood = Optional.of(food);
		allFood = new ArrayList<>();
	}
	
	@After
	public void teardown() throws Exception {
		
	}
	
	@Test
	public void testGetAllFoodSuccess() {
		allFood.add(food);
		food = new Food(1003,"Mango","SR Legacy","2019-01-04","","","","Fruit",nutrientsList);
		allFood.add(food);
		food = new Food(1005,"Apple Juice","Branded","2019-12-06","Water Apple Sugar","Tropicana","","Beverages",nutrientsList);
		allFood.add(food);
		when(foodRepo.findAll()).thenReturn(allFood);
		List<Food> fetchedFoodList = foodServiceImpl.getAllFood();
		assertEquals(allFood, fetchedFoodList);
		verify(foodRepo).findAll();
	}
	
	@Test
	public void testGetAllFoodFailure() {
		when(foodRepo.findAll()).thenReturn(null);
		List<Food> fetchedFoodList = foodServiceImpl.getAllFood();
		assertEquals(null, fetchedFoodList);
		verify(foodRepo).findAll();
	}
	
	@Test
	public void testSaveFoodSuccess() {
		when(foodRepo.findById(food.getFdcId())).thenReturn(Optional.empty());
		when(foodRepo.save(Mockito.any(Food.class))).thenReturn(food);
		Food fetchedFood = foodServiceImpl.saveFood(food);
		assertEquals(food, fetchedFood);
		verify(foodRepo).findById(Mockito.any());
		verify(foodRepo).save(Mockito.any());
	}
	
	@Test
	public void testSaveFoodFailure() {
		when(foodRepo.findById(Mockito.any())).thenReturn(optFood);
		Food fetched = foodServiceImpl.saveFood(food);
		assertEquals(null, fetched);
		verify(foodRepo).findById(Mockito.any());
	}
	
	@Test
	public void testGetFoodByIdSuccess() {
		when(foodRepo.findById(Mockito.anyInt())).thenReturn(optFood);
		Food fetchedFood = foodServiceImpl.getFoodById(food.getFdcId());
		
		assertEquals(food, fetchedFood);
		verify(foodRepo).findById(Mockito.any());
	}
	
	@Test
	public void testGetFoodByIdFailure() {
		when(foodRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		Food fetched = foodServiceImpl.getFoodById(food.getFdcId());
		assertEquals(null, fetched);
		verify(foodRepo).findById(Mockito.any());
		
	}
	
}
