package com.fdmgroup.Controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Status;

@Controller
public class IssueApprovedController {

	@Resource(name = "issueDAOBean")
	IssueDAO issueDAO;

	@Resource(name = "userDAOBean")
	UserDAO userDAO;

	@RequestMapping(value = "issue/update/approved/{id}")
	public String approve(@PathVariable long id, HttpServletRequest request) {
		Issue issue = issueDAO.getIssue(id);
		if(issue == null) {
			request.getSession().setAttribute("error", "issue not found");
			return "dashboard/customer";
		}
		issue.setStatus(Status.APPROVED);
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("userName");
		User user = userDAO.get(name);
		List<Issue> list = user.getIssues();
		session.setAttribute("issueList", list);
		return "dashboard/customer";

	}

}
