package com.project.service;

import java.util.List;

import com.project.model.Food;

public interface FoodService {

	public List<Food> getAllFood();
	
	public Food saveFood(Food food);
	
	public Food getFoodById(Integer id);
}

