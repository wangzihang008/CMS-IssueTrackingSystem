package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;


import static org.mockito.Mockito.*;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Type;



public class LoginControllerTest {
	@Mock //=mock(EntityManagerFactory.class)
	private UserDAO uDao;
	@Mock
	private IssueDAO iDao;
	@InjectMocks
	private LoginController lc = new LoginController();
	
	@Before
	public void startInjectMocks() {
		MockitoAnnotations.initMocks(this);
		
	}
	@Test
	public void given_not_logged_in_then_return_GoToLogin() {

		LoginController lc = new LoginController();
		Model mockModel = mock(Model.class);
		HttpSession mockSession = mock(HttpSession.class);
		String nextPage = lc.goToLogin(mockModel,mockSession);
		when(mockSession.getAttribute("userName")).thenReturn(null);
		assertEquals("login", nextPage);
	}
	@Test
	public void given_customer_have_logged_in_then_return_customer_page() {

		LoginController lc = new LoginController();
		Model mockModel = mock(Model.class);
		HttpSession mockSession = mock(HttpSession.class);
		when(mockSession.getAttribute("userName")).thenReturn("ExampleName");
		when(mockSession.getAttribute("userType")).thenReturn(Type.BASIC_USER);
		String nextPage = lc.goToLogin(mockModel,mockSession);
		
		assertEquals("dashboard/customer", nextPage);
	}
	@Test
	public void given_general_admin_have_logged_in_then_return_admin_page() {

		LoginController lc = new LoginController();
		Model mockModel = mock(Model.class);
		HttpSession mockSession = mock(HttpSession.class);
		when(mockSession.getAttribute("userName")).thenReturn("ExampleName");
		when(mockSession.getAttribute("userType")).thenReturn(Type.GENERAL_ADMIN);
		String nextPage = lc.goToLogin(mockModel,mockSession);
		
		assertEquals("dashboard/admin", nextPage);
	}
	@Test
	public void given_dep_admin_have_logged_in_then_return_depadmin_page() {

		LoginController lc = new LoginController();
		Model mockModel = mock(Model.class);
		HttpSession mockSession = mock(HttpSession.class);
		when(mockSession.getAttribute("userName")).thenReturn("ExampleName");
		when(mockSession.getAttribute("userType")).thenReturn(Type.DEPARTMENT_ADMIN);
		String nextPage = lc.goToLogin(mockModel,mockSession);
		
		assertEquals("dashboard/depadmin", nextPage);
	}
	
	@Test
	public void given_user_does_not_exist_then_return_wrongpassword() {
		
		User mockUser = mock(User.class);
		Model mockModel = mock(Model.class);
		HttpSession mockSession = mock(HttpSession.class);
		when(mockUser.getUsername()).thenReturn("ExampleName");
		when(mockUser.getPassword()).thenReturn("ExamplePassword");
		when(uDao.get("ExampleName")).thenReturn(null);
		String nextPage = lc.Login(mockModel, mockUser, mockSession );


		assertEquals("wrongpassword", nextPage);

	}
	@Test
	public void given_password_is_not_right_then_return_wrongpassword() {
		
		User mockUser = mock(User.class);
		Model mockModel = mock(Model.class);
		HttpSession mockSession = mock(HttpSession.class);
		when(mockUser.getUsername()).thenReturn("ExampleName");
		when(mockUser.getPassword()).thenReturn("ExamplePassword");
		User mockUser2 = mock(User.class);
		when(uDao.get("ExampleName")).thenReturn(mockUser2);
		when(mockUser2.getPassword()).thenReturn("ExamplePasswordqqqqq");
		String nextPage = lc.Login(mockModel, mockUser, mockSession);


		assertEquals("wrongpassword", nextPage);

	}

	@Test
	public void given_type_is_BasicUser_then_goToCustomer() {
		
		User mockUser = mock(User.class);
		Model mockModel = mock(Model.class);
		HttpSession mockSession = mock(HttpSession.class);
		when(mockUser.getUsername()).thenReturn("ExampleName");
		when(mockUser.getPassword()).thenReturn("ExamplePassword");
		User mockUser2 = mock(User.class);
		when(uDao.get("ExampleName")).thenReturn(mockUser2);
		when(mockUser2.getPassword()).thenReturn("ExamplePassword");
		when(mockUser2.getType()).thenReturn(Type.BASIC_USER);
		String nextPage = lc.Login(mockModel, mockUser, mockSession);


		assertEquals("dashboard/customer", nextPage);

	}
	@Test
	public void given_type_is_Admin_then_goToAdmin() {
		
		User mockUser = mock(User.class);
		Model mockModel = mock(Model.class);
		HttpSession mockSession = mock(HttpSession.class);
		when(mockUser.getUsername()).thenReturn("ExampleName");
		when(mockUser.getPassword()).thenReturn("ExamplePassword");
		User mockUser2 = mock(User.class);
		when(uDao.get("ExampleName")).thenReturn(mockUser2);
		when(mockUser2.getPassword()).thenReturn("ExamplePassword");
		when(mockUser2.getType()).thenReturn(Type.GENERAL_ADMIN);
		String nextPage = lc.Login(mockModel, mockUser, mockSession);


		assertEquals("dashboard/admin", nextPage);

	}

	@Test
	public void given_type_is_DepAdmin_then_goToDepAdmin() {
		
		User mockUser = mock(User.class);
		Model mockModel = mock(Model.class);
		HttpSession mockSession = mock(HttpSession.class);
		when(mockUser.getUsername()).thenReturn("ExampleName");
		when(mockUser.getPassword()).thenReturn("ExamplePassword");
		User mockUser2 = mock(User.class);
		when(uDao.get("ExampleName")).thenReturn(mockUser2);
		when(mockUser2.getUsername()).thenReturn("qz");
		when(mockUser2.getPassword()).thenReturn("ExamplePassword");
		when(mockUser2.getType()).thenReturn(Type.DEPARTMENT_ADMIN);
		when(mockUser2.getDepartment()).thenReturn(null);
		when(iDao.getIssuesByDepartment(mockUser2.getDepartment())).thenReturn(null);
		String nextPage = lc.Login(mockModel, mockUser, mockSession);

		verify(mockModel).addAttribute("active_user", mockUser2.getUsername());
		verify(iDao).getIssuesByDepartment(mockUser2.getDepartment());
		verify(mockModel).addAttribute("issues", null);
		assertEquals("dashboard/depadmin", nextPage);

	}


	

}
