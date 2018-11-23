package com.fdmgroup.Controllers;



import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Status;
import com.fdmgroup.Enum.Type;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

	@Resource(name = "userDAOBean")
	private UserDAO uDao;
	

	@RequestMapping(method = RequestMethod.GET)
	public String goToRegister(Model model) {
		model.addAttribute("user_register", new User());
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String Register(User user, Model model) {
		String name = user.getUsername();
		String password = user.getPassword();
		if (password.equals("") 
				|| uDao.get(name) != null 
				|| name.isEmpty()) {
			model.addAttribute("user_register", new User());
			System.out.println(uDao.get(name));
			model.addAttribute("fail_msg", "Invalid username or user has existed");
			return "register";
		}
		else {
			user.setStatus(Status.ACTIVE);
			user.setType(Type.CUSTOMER);
			uDao.addUser(user);
			return "index";
		}

	}
}