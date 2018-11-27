package com.fdmgroup.Controllers;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fdmgroup.DAO.IssueDAO;


@Controller
@RequestMapping(value = "/issue/detail")
public class IssueDetailController {

	@Resource(name = "issueDAOBean")
	private IssueDAO issueDAO;

	@RequestMapping(method = RequestMethod.GET)
	public String goToIssueDetail(Model model) {
		return "issue/detail";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String goToDashboard() {
		return "dashboard/?";
	}
	
	@RequestMapping(value="/issue/detail/{issueId}", method=RequestMethod.GET)
	public String goToIssueDetailByAdmin(@PathVariable String issueId) {
		return "";
	}
}
