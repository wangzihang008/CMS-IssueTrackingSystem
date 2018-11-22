package com.fdmgroup.Controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Type;

@Controller
@SessionAttributes(value = "blank_user_login")
@RequestMapping(value = "/login")
public class LoginController {

	@Resource(name = "userDAOBean")
	private UserDAO uDao;

	@RequestMapping(method = RequestMethod.GET)
	public String goToLogin(Model model, HttpSession session) {
		if (session.getAttribute("userName") != null) {
			if (session.getAttribute("userType").equals(Type.CUSTOMER)) {
				return "dashboard/customer";
			} else if (session.getAttribute("userType").equals(Type.ADMIN)) {
				return "dashboard/admin";
			} else {
				return "dashboard/depadmin";
			}
		} else {
			model.addAttribute("blank_user_login", new User());
			return "login";
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public String Login(User user, HttpSession session) {
		String name = user.getUsername();
		String password = user.getPassword();
		User user2 = uDao.get(name);

		if (user2 == null || !user2.getPassword().equals(password)) {
			return "wrongpassword";
		} else {
			session.setAttribute("userName", name);
			session.setAttribute("userId", user2.getId());
			session.setAttribute("userType", user2.getType());
			if (user2.getType().equals(Type.CUSTOMER)) {
				return "dashboard/customer";
			} else if (user2.getType().equals(Type.ADMIN)) {
				return "dashboard/admin";
			} else {
				return "dashboard/depadmin";
			}
		}
	}
}