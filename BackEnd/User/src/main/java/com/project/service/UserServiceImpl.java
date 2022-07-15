package com.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.project.exception.UserAlreadyExistsException;
import com.project.exception.UserNotFoundException;
import com.project.model.User;
import com.project.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	public User saveUser(User user) throws UserAlreadyExistsException {
		Optional<User> u =  userRepo.findById(user.getUsername());
		if(u.isPresent())
			throw new UserAlreadyExistsException("Username already exists");
		else
		{
			String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			user.setPassword(hashpw);
			userRepo.save(user);
			return user;
	
		}
	}

	public User getUser(String username, String password) throws UserNotFoundException {
		Optional<User> u = userRepo.findById(username);
		User user = null;
		if(u.isPresent()) {
			user = u.get();
			boolean matched = BCrypt.checkpw(password, user.getPassword());
			if(!matched)
				user = null;
		}
		else
			throw new UserNotFoundException();
		return user;
	}

}
