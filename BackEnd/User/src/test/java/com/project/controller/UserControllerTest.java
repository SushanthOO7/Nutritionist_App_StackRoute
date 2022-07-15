package com.project.controller;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.project.controller.UserController;
import com.project.exception.UserAlreadyExistsException;
import com.project.model.User;
import com.project.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
	// Sending request to controller
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserService userService;
	private User user;
	Optional<User> optUser;	
	String token;
	Map<String, String> map;
	@Before
	public void setUp() throws Exception {
		user = new User("hera","pass","h@1","hh");
		map = new HashMap<String, String>();
	}
	@After
	public void tearDown() throws Exception {
	}
	//testSaveUser is for Register
	@Test
	public void testRegisterUserSuccess() throws Exception{
		when(userService.saveUser(Mockito.any(User.class))).thenReturn(user);
		mockMvc.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isCreated()).andDo(print());
		verify(userService).saveUser(Mockito.any(User.class));
	}
	
	
	
	@Test
	public void testRegisterUserFailure() throws UserAlreadyExistsException, Exception{
		when(userService.saveUser(Mockito.any(User.class))).thenThrow(UserAlreadyExistsException.class);
		mockMvc.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isConflict()).andDo(print());
		verify(userService).saveUser(Mockito.any(User.class));
	}
	
	
	@Test
	public void testLoginUserSuccess() throws Exception {
		when(userService.getUser(Mockito.anyString(), Mockito.anyString())).thenReturn(user);
		token = Jwts.builder().
				setId(user.getUsername())
				.setIssuedAt(new Date()).
				signWith(SignatureAlgorithm.HS256, "usersecretkey").
				compact();
		map.put("token", token);
		map.put("message", "User Successfully logged in");
		mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user))
				.param("username", user.getUsername())
				.param("password", user.getPassword()))
		.andExpect(status().isOk()).andDo(print());
		
	}
	
	public static String asJsonString(final Object obj) {
		try {

			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
