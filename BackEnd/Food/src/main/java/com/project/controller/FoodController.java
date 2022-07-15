package com.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Food;
import com.project.service.FavoriteService;
import com.project.service.FoodService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/food")
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private FavoriteService favoriteService;
	
	@GetMapping("/isAuthenticated")
	public ResponseEntity<Map<String, String>> isAuthenticated() {
		Map<String, String> map = new HashMap<>();
		map.put("isAuthenticated", "true");
		return new ResponseEntity<>(map, HttpStatus.OK);
		
	}

	@PostMapping("/saveFood")
	public ResponseEntity<Food> saveFood(@RequestBody Food food) {
		Food foodSaved = foodService.saveFood(food);
		if(foodSaved != null)
			return new ResponseEntity<>(foodSaved,HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/getFood")
	public ResponseEntity<List<Food>> getFood() {
		List<Food> foodList = foodService.getAllFood();
		if(foodList != null) {
			return new ResponseEntity<>(foodList,HttpStatus.OK);
		}
		else
			return null;
	}
	
	@PostMapping("/saveFavorite")
	public ResponseEntity<Food> addToFavorite(@RequestBody Food food, ServletRequest req) {
		String username = (String)req.getAttribute("claims");
		Food fetchedFood = foodService.getFoodById(food.getFdcId());
		if(fetchedFood != null) {
			favoriteService.addToFavorite(username, food.getFdcId());
			return new ResponseEntity<>(fetchedFood,HttpStatus.OK);
		}
		else {
			Food foodSaved = foodService.saveFood(food);
			if(foodSaved != null) {
				favoriteService.addToFavorite(username, food.getFdcId());
				return new ResponseEntity<>(foodSaved,HttpStatus.OK);				
			}
			else
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	

	
	@PostMapping("/getFavorite")
	public ResponseEntity<List<Food>> getFavorite(ServletRequest req) {
		String username = (String)req.getAttribute("claims");
		List<Integer> favList = favoriteService.getFavorite(username);
		if(favList != null) {
			List<Food> foodlist = new ArrayList<>();
			for (Integer fdcId : favList) {
				Food food = foodService.getFoodById(fdcId);
				foodlist.add(food);
			}
		return new ResponseEntity<>(foodlist,HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.CONFLICT);
	}
	

	
	@PostMapping("/deleteFavorite")
	public ResponseEntity<List<Food>> deleteFavorite(@RequestParam("fdcId") Integer fdcId, ServletRequest req) {
		String username = (String)req.getAttribute("claims");
		boolean result =favoriteService.deleteFavorite(username, fdcId);
		if(result == true)
		{
			List<Integer> favList = favoriteService.getFavorite(username);
			List<Food> foodlist = new ArrayList<>();
			for (Integer favId : favList) {
				Food food = foodService.getFoodById(favId);
				foodlist.add(food);
			}
			return new ResponseEntity<>(foodlist,HttpStatus.OK);	
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	
}
