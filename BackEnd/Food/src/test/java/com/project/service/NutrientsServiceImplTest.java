package com.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.project.model.Nutrients;
import com.project.repository.NutrientsRepository;

@RunWith(MockitoJUnitRunner.class)
public class NutrientsServiceImplTest {

	@Mock
	private NutrientsRepository nutrientRepo;
	
	@InjectMocks
	private NutrientsServiceImpl nutrientServiceImpl;
	
	Nutrients nutrient;
	List<Nutrients> allNutrient; 
	
	@Before
	public void setup() {
		nutrient = new Nutrients(111,"Water","86.2","g","");
		allNutrient = new ArrayList<Nutrients>();
	}
	
	@After
	public void teardown() {
		
	}
	
	@Test
	public void testGetAllNutrientsSuccess() {
		allNutrient.add(nutrient);
		nutrient = new Nutrients(112,"Protein","0.5","g","");
		allNutrient.add(nutrient);
		nutrient = new Nutrients(114,"Calcium, Ca","11","mg","");
		allNutrient.add(nutrient);
		when(nutrientRepo.findAll()).thenReturn(allNutrient);
		List<Nutrients> fetchedNutrientList = nutrientServiceImpl.getAllNutrients();
		assertEquals(allNutrient, fetchedNutrientList);
		verify(nutrientRepo).findAll();
	}
	
	@Test
	public void testGetAllNutrientsFailure() {
		when(nutrientRepo.findAll()).thenReturn(null);
		List<Nutrients> fetchedNutrientList = nutrientServiceImpl.getAllNutrients();
		assertEquals(null, fetchedNutrientList);
		verify(nutrientRepo).findAll();
	}
	
	@Test
	public void testSaveNutrientsSuccess() {
		when(nutrientRepo.save(Mockito.any(Nutrients.class))).thenReturn(nutrient);
		Nutrients fetchedNutrients = nutrientServiceImpl.saveNutrients(nutrient);
		assertEquals(nutrient, fetchedNutrients);
		verify(nutrientRepo).save(Mockito.any());
	}
	
}
