package com.fdmgroup.Controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fdmgroup.DAO.IssueDetailDAO;

@Controller
@RequestMapping(value = "/issue/detail")
public class IssueDetailController {

	@Resource(name = "issueDetailDAOBean")
	private IssueDetailDAO issueDetailDAO;

	@RequestMapping(method = RequestMethod.GET)
	public String goToIssueDetail(Model model) {
		return "issue/detail";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String goToDashboard(HttpSession session) {
		String url = (String) session.getAttribute("userType");
		return "dashboard/" + url;
	}

}
