package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Type;



public class LogoutControllerTest {
	@Mock //=mock(EntityManagerFactory.class)
	private UserDAO uDao;
	@InjectMocks
	private LogoutController lc = new LogoutController();
	
	@Before
	public void startInjectMocks() {
		MockitoAnnotations.initMocks(this);
		
	}
	@Test
	public void return_GoToLogout() {

		LogoutController lc = new LogoutController();
		HttpSession mockSession = mock(HttpSession.class);
		String nextPage = lc.goToLogout(mockSession);
		assertEquals("logout", nextPage);
	}
	
	
	

}
