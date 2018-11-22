package com.fdmgroup.Controllers;

import javax.annotation.Resource;

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
		public String goToLogin(Model model) {
			model.addAttribute("blank_user_login", new User());
			return "login";
		}

		@RequestMapping(method = RequestMethod.POST)
		public String Login(User user) {
			String name = user.getUsername();
			String password = user.getPassword();
			User user2 = uDao.get(name);
			
			if(user2 == null || !user2.getPassword().equals(password)) {
				return "wrongpassword";
				
			}
			
			else if (user2.getType().equals(Type.BASIC_USER)){
				return "dashboard/customer";
				
			}
			else if (user2.getType().equals(Type.GENERAL_ADMIN)){
				return "dashboard/admin";
				
			}
			else {
				return "dashboard/depadmin";
				
			}

		}
}

