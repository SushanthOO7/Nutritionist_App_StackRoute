package com.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.model.Nutrients;

public interface NutrientsRepository extends MongoRepository<Nutrients, Integer>{

}
