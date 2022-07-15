package com.project.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.exception.UserAlreadyExistsException;
import com.project.exception.UserNotFoundException;
import com.project.model.User;
import com.project.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
	

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		try {
		userService.saveUser(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
		}
		catch(UserAlreadyExistsException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		catch(Exception e) {

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> loginUser(@RequestParam("username") String username, @RequestParam("password") String password) throws UserNotFoundException {
		User user = userService.getUser(username, password);
		if(user == null) {
			throw new UserNotFoundException();
		}
		String token =
				Jwts.builder().
				setId(user.getUsername())
				.setIssuedAt(new Date()).
				signWith(SignatureAlgorithm.HS256, "usersecretkey").
				compact();
		
		Map<String, String> map1 = new HashMap<>();
		map1.put("token", token);
		map1.put("message", "User Successfully logged in");
		return new ResponseEntity<>(map1, HttpStatus.OK);
		
		
	}
}
