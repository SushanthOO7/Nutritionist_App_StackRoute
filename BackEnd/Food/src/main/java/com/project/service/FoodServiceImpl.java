package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Food;
import com.project.repository.FoodRepository;

@Service
public class FoodServiceImpl implements FoodService{
	
	@Autowired
	private FoodRepository foodRepo;

	@Override
	public List<Food> getAllFood() {
		List<Food> foodList = foodRepo.findAll();
		if(foodList != null)
			return foodList;
		else 
			return null;
	}

	@Override
	public Food saveFood(Food food) {
		Optional<Food> fetchedFood = foodRepo.findById(food.getFdcId());
		if(fetchedFood.isPresent())
			return null;
		else {
			return foodRepo.save(food);
		}
			
	}

	@Override
	public Food getFoodById(Integer id) {
		Optional<Food> fetchedFood = foodRepo.findById(id);
		if(fetchedFood.isPresent())
			return fetchedFood.get();
		return null;
	}

}
