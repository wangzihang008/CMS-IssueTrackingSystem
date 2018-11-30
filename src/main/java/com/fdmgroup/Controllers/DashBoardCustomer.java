package com.fdmgroup.Controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.Entities.Issue;

@Controller
public class DashBoardCustomer {

	@Resource(name = "issueDAOBean")
	private IssueDAO issueDAO;

	@RequestMapping(value = "/dashboard/customer")
	public String gotoDashBoardCustomer(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		long userId = (long) session.getAttribute("userId");
		List<Issue> list = issueDAO.getIssuesByUserId(userId);
		session.setAttribute("issueList", list);
		return "dashboard/customer";
	}
}
