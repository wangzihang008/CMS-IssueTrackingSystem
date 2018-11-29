package com.fdmgroup.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Status;
import com.fdmgroup.Enum.Type;
import com.fdmgroup.Services.AdminDashboardService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@Resource(name = "userDAOBean")
	private UserDAO uDao;

	@Resource(name = "issueDAOBean")
	private IssueDAO iDao;

	@Resource(name = "adminDashboardServiceBean")
	private AdminDashboardService ads;

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
	public String Login(Model model, User user, HttpSession session, HttpServletRequest request) {

		model.addAttribute("blank_user_login", user);
		String name = user.getUsername();
		String password = user.getPassword();
		User user2 = uDao.get(name);

		if (user2 == null || !user2.getPassword().equals(password)) {
			model.addAttribute("fail_msg", "Invalid username or password");
			return "login";

		} else if (user2.getStatus().equals(Status.INACTIVE)) {
			request.setAttribute("fail_msg", "Error");
			return "login";
		}

		else {
			session.setAttribute("userName", name);
			session.setAttribute("userId", user2.getId());
			session.setAttribute("userType", user2.getType());
			if (user2.getType().equals(Type.CUSTOMER)) {
				List<Issue> list = user2.getIssues();
				session.setAttribute("issueList", list);
				return "dashboard/customer";
			} else if (user2.getType() == Type.ADMIN) {
				ArrayList<Issue> issues = ads.getAllIssues();
				if (!issues.isEmpty()) {
					model.addAttribute("issues", issues);
				}
				return "dashboard/admin";

			} else {
				model.addAttribute("active_user", user2.getUsername());
				List<Issue> issues = iDao.getAssignedIssuesByDepartment(user2.getDepartment());
				model.addAttribute("issues", issues);
				return "dashboard/depadmin";
			}

		}
	}

}
