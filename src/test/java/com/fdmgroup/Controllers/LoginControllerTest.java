package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Type;



public class LoginControllerTest {
	@Mock //=mock(EntityManagerFactory.class)
	private UserDAO uDao;
	@InjectMocks
	private LoginController lc = new LoginController();
	
	@Before
	public void startInjectMocks() {
		MockitoAnnotations.initMocks(this);
		
	}
	@Test
	public void return_GoToLogin() {

		LoginController lc = new LoginController();
		Model mockModel = mock(Model.class);
		String nextPage = lc.goToLogin(mockModel);
		assertEquals("login", nextPage);
	}
	
	@Test
	public void given_user_does_not_exist_then_return_wrongpassword() {
		
		User mockUser = mock(User.class);
		when(mockUser.getUsername()).thenReturn("ExampleName");
		when(mockUser.getPassword()).thenReturn("ExamplePassword");
		when(uDao.get("ExampleName")).thenReturn(null);
		String nextPage = lc.Login(mockUser);


		assertEquals("wrongpassword", nextPage);

	}
	@Test
	public void given_password_is_not_right_then_return_wrongpassword() {
		
		User mockUser = mock(User.class);
		when(mockUser.getUsername()).thenReturn("ExampleName");
		when(mockUser.getPassword()).thenReturn("ExamplePassword");
		User mockUser2 = mock(User.class);
		when(uDao.get("ExampleName")).thenReturn(mockUser2);
		when(mockUser2.getPassword()).thenReturn("ExamplePasswordqqqqq");
		String nextPage = lc.Login(mockUser);


		assertEquals("wrongpassword", nextPage);

	}

	@Test
	public void given_type_is_BasicUser_then_goToCustomer() {
		
		User mockUser = mock(User.class);
		when(mockUser.getUsername()).thenReturn("ExampleName");
		when(mockUser.getPassword()).thenReturn("ExamplePassword");
		User mockUser2 = mock(User.class);
		when(uDao.get("ExampleName")).thenReturn(mockUser2);
		when(mockUser2.getPassword()).thenReturn("ExamplePassword");
		when(mockUser2.getType()).thenReturn(Type.BASIC_USER);
		String nextPage = lc.Login(mockUser);


		assertEquals("dashboard/customer", nextPage);

	}
	@Test
	public void given_type_is_Admin_then_goToAdmin() {
		
		User mockUser = mock(User.class);
		when(mockUser.getUsername()).thenReturn("ExampleName");
		when(mockUser.getPassword()).thenReturn("ExamplePassword");
		User mockUser2 = mock(User.class);
		when(uDao.get("ExampleName")).thenReturn(mockUser2);
		when(mockUser2.getPassword()).thenReturn("ExamplePassword");
		when(mockUser2.getType()).thenReturn(Type.GENERAL_ADMIN);
		String nextPage = lc.Login(mockUser);


		assertEquals("dashboard/admin", nextPage);

	}

	@Test
	public void given_type_is_DepAdmin_then_goToDepAdmin() {
		
		User mockUser = mock(User.class);
		when(mockUser.getUsername()).thenReturn("ExampleName");
		when(mockUser.getPassword()).thenReturn("ExamplePassword");
		User mockUser2 = mock(User.class);
		when(uDao.get("ExampleName")).thenReturn(mockUser2);
		when(mockUser2.getPassword()).thenReturn("ExamplePassword");
		when(mockUser2.getType()).thenReturn(Type.DEPARTMENT_ADMIN);
		String nextPage = lc.Login(mockUser);


		assertEquals("dashboard/depadmin", nextPage);

	}


	

}
