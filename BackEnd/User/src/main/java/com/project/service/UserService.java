package com.project.service;

import com.project.exception.UserAlreadyExistsException;
import com.project.exception.UserNotFoundException;
import com.project.model.User;

public interface UserService {
	
	public User saveUser(User user) throws UserAlreadyExistsException;
	
	public User getUser(String username, String password) throws UserNotFoundException;

}
