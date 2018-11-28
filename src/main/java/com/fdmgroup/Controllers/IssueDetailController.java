package com.fdmgroup.Controllers;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.IssueDetail;
import com.fdmgroup.Services.IssueDetailService;

@Controller
@RequestMapping(value = "/issue/detail/{issueId}")
public class IssueDetailController {

	@Resource(name = "issueDetailServiceBean")
	private IssueDetailService ids;

	@Resource(name = "issueDAOBean")
	private IssueDAO issueDAO;

	@RequestMapping(method = RequestMethod.GET)
	public String goToIssueDetail(@PathVariable long issueId, Model model) {
		Issue accessedIssue = issueDAO.getIssue(issueId);
		model.addAttribute("active_issue", accessedIssue);
		List<IssueDetail> details = ids.displayAllDetails(accessedIssue);
		model.addAttribute("details", details);
		return "issue/detail";
	}
}
