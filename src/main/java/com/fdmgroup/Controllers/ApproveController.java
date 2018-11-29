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
	private IssueDAO issueDAO;
	
	@RequestMapping(value="issue/update/approve/{id}")
	public String toApprove(@PathVariable int id, HttpServletRequest request) {
		Issue issue = issueDAO.getIssue(id);
		List<IssueDetail> list = issue.getDetails();
		request.getSession().setAttribute("list", list);
		return "issue/update/approve";
	}
	
}
