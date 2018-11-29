package com.fdmgroup.Services;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.DAO.IssueDetailDAO;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.IssueDetail;
import com.fdmgroup.Enum.Status;

public class IssueUpdateCustomerService {

	@Resource(name = "issueDAOBean")
	private IssueDAO issueDao;
	@Resource(name = "calendarBean")
	private Calendar calendar;
	@Resource(name = "issueDetailDAOBean")
	private IssueDetailDAO issueDetailDao;
	@Resource(name = "issueDetailBean")
	private IssueDetail issueDetail;

	public String update(String issueId, IssueDetail issueDetail, HttpServletRequest req, HttpSession session) {
		String result = "/dashboard/customer";
		if (session.getAttribute("userType") == null) {
			result = "/index";
			req.setAttribute("errorMessage", "You have not logged in. Please login first.");
		} else {
			Issue issue = issueDao.getIssue(Long.parseLong(issueId));

			if (issue == null) {
				req.setAttribute("errorMessage", "The issue does not exist!");
			} else if ((long) session.getAttribute("userId") != issue.getCreateUser().getId()) {
				req.setAttribute("errorMessage", "You have no right to update this issue");
			} else {
				issueDetail.setCreateDate(calendar);
				issueDetail.setIssue(issue);
				issueDetail.setStatus(Status.CUSTOMERUPDATE);
				issueDetail.setUser(issue.getCreateUser());
				issueDetailDao.addIssueDetail(issueDetail);
			}
		}

		return result;
	}

	public String check(String issueId, Model model, HttpServletRequest req, HttpSession session) {
		String result = "";
		if (session.getAttribute("userType") == null) {
			result = "/index";
			req.setAttribute("errorMessage", "You have not logged in. Please login first.");
		} else {
			result = "/dashboard/" + session.getAttribute("userType");
			Issue issue = issueDao.getIssue(Long.parseLong(issueId));
			if (issue == null) {
				req.setAttribute("errorMessage", "The issue does not exist!");
			} else if ((long) session.getAttribute("userId") != issue.getCreateUser().getId()) {
				req.setAttribute("errorMessage", "You have no right to update this issue");
			} else {
				model.addAttribute("newIssueDetail", issueDetail);
				req.setAttribute("issueId", issueId);
				result = "/issue/update/customer";
			}
		}

		return result;
	}

}
