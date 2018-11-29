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

import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Type;

@Controller
public class DashBoardCustomer {

	@Resource(name="userDAOBean")
	UserDAO userDAO;
	
	@RequestMapping(value="dashboard/customer")
	public String gotoDashBoardCustomer(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("userName");
		System.out.println(name);
		if(name == null) {
			return "index";
		}
		User user = userDAO.get(name);
		if(user == null) {
			Issue issue = new Issue();
			issue.setId(909090909);
			issue.setTitle("you dont have any issue");
			List<Issue> list = new ArrayList<Issue>();
			list.add(issue);
			request.setAttribute("issueList", list);
		} else {
			List<Issue> list = user.getIssues();
			session.setAttribute("issueList", list);
		}	
		return "dashboard/customer";
	}
}
