package com.fdmgroup.Controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.Entities.IssueDetail;
import com.fdmgroup.Services.IssueRegisterService;

@Controller
@RequestMapping(value="issue/register")
public class IssueRegisterController {
	
	@Resource(name="customerIssueRegisterBean")
	private IssueRegisterService irs;

	@RequestMapping(method=RequestMethod.GET)
	public String goToIssueRegister(Model model) {
		// TODO Auto-generated method stub
		model.addAttribute("newIssueDetail", new IssueDetail());
		return "issue/register";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String goToIssueRegister(IssueDetail issueDetail, HttpServletRequest req) {
		// TODO Auto-generated method stub
		irs.register(issueDetail, req);
		
		return "issue/register";
	}
}
