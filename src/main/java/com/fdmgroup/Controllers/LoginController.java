package com.fdmgroup.Controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Type;

@Controller
@RequestMapping(value = "/login")
@SessionAttributes(value = "blank_user_login")
public class LoginController {

	@Resource(name = "userDAOBean")
	private UserDAO uDao;

	@Resource(name = "issueDAOBean")
	private IssueDAO iDao;

	@RequestMapping(method = RequestMethod.GET)
	public String goToLogin(Model model) {
		model.addAttribute("blank_user_login", new User());
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String Login(Model model, User user) {
		String name = user.getUsername();
		String password = user.getPassword();
		model.addAttribute("blank_user_login", user);
		User user2 = uDao.get(name);

		if (user2 == null || !user2.getPassword().equals(password)) {
			return "wrongpassword";

		}else if (user2.getType().equals(Type.BASIC_USER)) {
			return "dashboard/customer";

		}else if (user2.getType().equals(Type.GENERAL_ADMIN)) {
			return "dashboard/admin";

		}else {

			User loggedInInUser = uDao.get(user.getUsername());
			model.addAttribute("active_user", loggedInInUser.getUsername());
			List<Issue> issues = iDao.getIssuesByDepartment(loggedInInUser.getDepartment());
			model.addAttribute("issues", issues);
			return "dashboard/depadmin";

		}

	}
}
