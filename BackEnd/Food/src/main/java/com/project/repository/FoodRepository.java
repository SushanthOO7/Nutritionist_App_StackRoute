package com.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.model.Food;

public interface FoodRepository extends MongoRepository<Food, Integer> {

}
