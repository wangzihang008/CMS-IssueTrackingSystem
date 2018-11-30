package com.fdmgroup.Controllers;

import java.util.Calendar;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.DAO.IssueDetailDAO;
import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.IssueDetail;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Status;

@Controller
public class IssueApprovedController {

	@Resource(name = "issueDAOBean")
	private IssueDAO issueDAO;

	@Resource(name = "userDAOBean")
	private UserDAO userDAO;

	@Resource(name = "issueDetailDAOBean")
	private IssueDetailDAO issueDetailDAO;

	@Resource(name = "calendarBean")
	private Calendar calender;

	@RequestMapping(value = "dashboard/issue/update/approved/{id}")
	public String approve(@PathVariable long id, HttpServletRequest request, Model model) {
		Issue issue = issueDAO.getIssue(id);
		if (issue == null) {
			request.getSession().setAttribute("error", "issue not found");
			return "dashboard/customer";
		} else {
			issue.setStatus(Status.APPROVED);
			issueDAO.update(issue);
			IssueDetail issueDetail = new IssueDetail();
			issueDetail.setContent("issue has been approved");
			issueDetail.setCreateDate(calender);
			issueDetail.setIssue(issue);
			HttpSession session = request.getSession();
			String name = (String) session.getAttribute("userName");
			User user = userDAO.get(name);
			issueDetail.setUser(user);
			issueDetailDAO.addIssueDetail(issueDetail);
			model.addAttribute("approveMsg", "Approve success");
			return "dashboard/customer";
		}
	}

}
