package com.project.service;

import java.util.List;

public interface FavoriteService {
	
	public boolean addToFavorite(String username, Integer fdcId);
	
	public List<Integer> getFavorite(String username);
	
	public boolean deleteFavorite(String username, Integer fdcId );
	
	public boolean addToMeal(String username, Integer fdcId);
	
	public List<Integer> getMeal(String username);
	
	public boolean deleteMeal(String username, Integer fdcId );
	
}
