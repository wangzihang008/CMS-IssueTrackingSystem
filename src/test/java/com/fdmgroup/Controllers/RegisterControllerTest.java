package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import org.springframework.ui.Model;

import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.User;

public class RegisterControllerTest {

	@Mock
	private UserDAO uDao;

	@Mock
	private Model mockModel;

	@InjectMocks
	private RegisterController rc = new RegisterController();

	@Before
	public void startInjectMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void return_GoToRegister() {

		RegisterController rc = new RegisterController();
		Model mockModel = mock(Model.class);
		String nextPage = rc.goToRegister(mockModel);
		assertEquals("register", nextPage);
	}

	@Test
	public void given_registerRequest_when_passwordIsEmpty_then_goesToFailure() {

		User mockUser = mock(User.class);
		when(mockUser.getUsername()).thenReturn("ExampleName");
		when(mockUser.getPassword()).thenReturn("");
		when(uDao.get("")).thenReturn(null);
		String nextPage = rc.Register(mockUser, mockModel);

		verify(mockModel).addAttribute("fail_msg", "Invalid username or user has existed");
		assertEquals("register", nextPage);
	}

	@Test
	public void given_registerRequest_when_usernameAlreadyExists_then_goesToFailure() {

		User mockUser = mock(User.class);
		when(mockUser.getUsername()).thenReturn("ExampleName");
		when(mockUser.getPassword()).thenReturn("ExamplePassword");
		when(uDao.get("ExampleName")).thenReturn(new User());
		String nextPage = rc.Register(mockUser, mockModel);

		verify(mockModel).addAttribute("fail_msg", "Invalid username or user has existed");
		assertEquals("register", nextPage);
	}

	@Test
	public void given_registerRequest_when_usernameIsBlank_then_goesToFailure() {
		
		User mockUser = mock(User.class);
		when(mockUser.getUsername()).thenReturn("");
		when(mockUser.getPassword()).thenReturn("ExamplePassword");
		when(uDao.get("")).thenReturn(null);
		String nextPage = rc.Register(mockUser, mockModel);

		verify(mockModel).addAttribute("fail_msg", "Invalid username or user has existed");
		assertEquals("register", nextPage);
	}

	@Test
	public void given_username_does_not_Exist_then_add_user_to_database() {

		User mockUser = mock(User.class);
		when(mockUser.getUsername()).thenReturn("ExampleName");
		when(mockUser.getPassword()).thenReturn("ExamplePassword");
		when(uDao.get("ExampleName")).thenReturn(null);
		String nextPage = rc.Register(mockUser, mockModel);

		assertEquals("index", nextPage);
	}

}
