package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Nutrients;
import com.project.repository.NutrientsRepository;

@Service
public class NutrientsServiceImpl implements NutrientsService{
	
	@Autowired
	private NutrientsRepository nutrientRepo;

	@Override
	public List<Nutrients> getAllNutrients() {
		List<Nutrients> nutrientsList = nutrientRepo.findAll();
		if(nutrientsList != null)
			return nutrientsList;
		else 
			return nutrientsList;

	}

	@Override
	public Nutrients saveNutrients(Nutrients nutrient) {
		return nutrientRepo.save(nutrient);
		
	}

}
