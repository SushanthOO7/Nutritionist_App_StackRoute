package com.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Favorite;
import com.project.repository.FavoriteRespository;

@Service
public class FavoriteServiceImpl implements FavoriteService{

	@Autowired
	private FavoriteRespository favoriteRepo;
	
	@Override
	public boolean addToFavorite(String username, Integer fdcId) {
		boolean flag = false;
		Optional<Favorite> fetchedFood = favoriteRepo.findById(username);
		if(fetchedFood.isPresent()) {
			Favorite favorite = fetchedFood.get();
			List<Integer> favList = favorite.getFavoriteList();
			for (Integer favId : favList) {
				if(favId.equals(fdcId)) {
					return false;
				}
			}
			if(flag == false)
				favorite.setFavoriteList(fdcId);
			favoriteRepo.save(favorite);
			return true;
		}
		else {
			List<Integer> list = new ArrayList<>();
			list.add(fdcId);
			Favorite ff = new Favorite(username, list, new ArrayList<>());
			favoriteRepo.save(ff);
			return true;
		}
		
	}

	@Override
	public List<Integer> getFavorite(String username) {

		Optional<Favorite> favorite = favoriteRepo.findById(username);
		if(favorite.isPresent()) {
			return favorite.get().getFavoriteList();
		}
		return null;
	}

	@Override
	public boolean deleteFavorite(String username, Integer fdcId) {
		Optional<Favorite> favorite = favoriteRepo.findById(username);
		if(favorite.isPresent()) {
			Favorite favoriteList = favorite.get();
			favoriteList.getFavoriteList().remove(fdcId);
			favoriteRepo.save(favoriteList);
			return true;
		}
		return false;
	}

	@Override
	public boolean addToMeal(String username, Integer fdcId) {
		boolean flag = false;
		Optional<Favorite> fetched = favoriteRepo.findById(username);
		if(fetched.isPresent()) {
			Favorite favorite = fetched.get();
			List<Integer> mealList = favorite.getMealList();
			for (Integer mealId : mealList) {
				if(mealId.equals(fdcId)) {
					flag = true;
				}
			}
			if(flag == false)
				favorite.setMealList(fdcId);
			favoriteRepo.save(favorite);
			return true;
		}
		else {
			List<Integer> mealList = new ArrayList<>();
			mealList.add(fdcId);
			Favorite ff = new Favorite(username, new ArrayList<>(),mealList);
			favoriteRepo.save(ff);
			return true;
		}
	}

	@Override
	public List<Integer> getMeal(String username) {
		 Optional<Favorite> meal = favoriteRepo.findById(username);
		if(meal.isPresent()) {
			return meal.get().getMealList();
		}
		return null;
	}

	@Override
	public boolean deleteMeal(String username, Integer fdcId) {
		Optional<Favorite> meal = favoriteRepo.findById(username);
		if(meal.isPresent()) {
			Favorite favorite = meal.get();
			favorite.getMealList().remove(fdcId);
			favoriteRepo.save(favorite);
			return true;
		}
		return false;
	}

}
