package com.fdmgroup.Controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fdmgroup.DAO.UserDAO;

@Controller
@RequestMapping(value = "/logout")
public class LogoutController {

	@Resource(name = "userDAOBean")
	private UserDAO uDao;

	@RequestMapping(method = RequestMethod.GET)
	public String goToLogout(HttpSession session) {
		session.invalidate();
		return "logout";
	}
}
