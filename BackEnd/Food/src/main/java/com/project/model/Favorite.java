package com.project.model;

import java.util.List;

import javax.persistence.Column;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "favorites")
public class Favorite {
	
	@Column(name= "username")
	private String _id;
	private List<Integer> favoriteList;
	private List<Integer> mealList;
	
	public Favorite() {}
	public Favorite(String _id, List<Integer> favoriteList, List<Integer> mealList) {
		super();
		this._id = _id;
		this.favoriteList = favoriteList;
		this.mealList  = mealList;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public List<Integer> getFavoriteList() {
		return favoriteList;
	}
	public void setFavoriteList(Integer favoriteList) {
		this.favoriteList.add(favoriteList);
	}
	public List<Integer> getMealList() {
		return mealList;
	}
	public void setMealList(Integer mealList) {
		this.mealList.add(mealList);
	}
	
		
}
