package com.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.project.exception.UserAlreadyExistsException;
import com.project.exception.UserNotFoundException;
import com.project.model.User;
import com.project.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
	
	@Mock
	private UserRepository userRepo;
		
	@InjectMocks
	private UserServiceImpl userService;
	
	private User user;
	private Optional<User> optUser;
	private Optional<User> optUserEmpty;
		
	@Before
	public void setUp() {
		user = new User("guest123", "guest123", "guest@gmail.com", "Guest");
		optUser = Optional.of(user);
		optUserEmpty = Optional.empty();
	}
	
	@After
	public void teardown() {
	}
	
	@Test
	public void testSaveUserSuccess() throws UserAlreadyExistsException {
		when(userRepo.findById(user.getUsername())).thenReturn(optUserEmpty);
		String hashpw = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt());
		user.setPassword(hashpw);
		User addedUser = userService.saveUser(user);
		assertEquals(addedUser.getName(), user.getName());
		assertEquals(addedUser.getEmail(), user.getEmail());
		assertEquals(addedUser.getUsername(), user.getUsername());
		assertEquals(addedUser.getPassword(),user.getPassword());
		verify(userRepo).findById(Mockito.anyString());
		
	}

	
	@Test(expected = UserAlreadyExistsException.class)
	public void testSaveUserFailure() throws UserAlreadyExistsException {
		when(userRepo.findById(Mockito.anyString())).thenReturn(optUser);
		userService.saveUser(user);
		verify(userRepo).findById(Mockito.anyString());
	}
	
	@Test(expected = UserNotFoundException.class)
	public void testGetUserFailure() throws UserNotFoundException {
		when(userRepo.findById(Mockito.anyString())).thenReturn(optUserEmpty);
		userService.getUser(user.getUsername(), user.getPassword());
		verify(userRepo).findById(Mockito.anyString());
	}


}
