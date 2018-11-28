package com.fdmgroup.Controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.IssueDetail;

@Controller
public class ApproveController {

	@Resource(name="issueDAOBean")
	IssueDAO issueDAO;
	
	@RequestMapping(value="issue/update/approve/{id}")
	public String toApprove(@PathVariable String id, HttpServletRequest request) {
		long issueID=Long.parseLong(id);
		Issue issue = issueDAO.getIssue(issueID);
		List<IssueDetail> list = issue.getDetails();
		request.getSession().setAttribute("list", list);
		return "issue/update/approve";
	}
	
}
