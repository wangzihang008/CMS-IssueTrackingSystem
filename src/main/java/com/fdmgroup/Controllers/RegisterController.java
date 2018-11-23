package com.fdmgroup.Controllers;



import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.User;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

	@Resource(name = "userDaoBean")
	private UserDAO uDao;
	

	@RequestMapping(method = RequestMethod.GET)
	public String goToRegister(Model model) {
		model.addAttribute("user_register", new User());
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String Register(User user) {
		String name = user.getUsername();
		String password = user.getPassword();
		if (password.equals("") 
				|| uDao.get(name) != null 
				|| name.isEmpty()) {
			
			return "failure";
		}
		else {
			uDao.addUser(user);
			return "index";
		}

	}
}