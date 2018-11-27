package com.fdmgroup.Controllers;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.DAO.IssueDetailDAO;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.IssueDetail;

@Controller
@RequestMapping(value = "/issue/detail/{issueId}")
public class IssueDetailController {

	@Resource(name = "issueDetailDAOBean")
	private IssueDetailDAO issueDetailDAO;

	@Resource(name = "issueDAOBean")
	private IssueDAO issueDAO;

	@RequestMapping(method = RequestMethod.GET)
	public String goToIssueDetail(@PathVariable long issueId, Model model) {
		System.out.println(issueId);
		Issue accessedIssue = issueDAO.getIssue(issueId);
		model.addAttribute("active_issue", accessedIssue);
		List<IssueDetail> details = issueDetailDAO.getIssueDetailsByIssue(accessedIssue);
		model.addAttribute("details", details);
		return "issue/detail";
	}
}
