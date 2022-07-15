package com.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.model.Favorite;

public interface FavoriteRespository extends MongoRepository<Favorite, String>{

}
