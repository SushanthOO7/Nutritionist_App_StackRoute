package com.project.service;

import java.util.List;

import com.project.model.Nutrients;

public interface NutrientsService {
	
	public List<Nutrients> getAllNutrients();
	
	public Nutrients saveNutrients(Nutrients nutrients);

}
