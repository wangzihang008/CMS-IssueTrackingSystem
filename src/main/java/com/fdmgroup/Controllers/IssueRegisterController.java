package com.fdmgroup.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.Entities.Issue;

@Controller
@RequestMapping(value="issue/register")
public class IssueRegisterController {
	
	

	@RequestMapping(method=RequestMethod.GET)
	public String goToIssueRegister(Model model) {
		// TODO Auto-generated method stub
		model.addAttribute("newIssue", new Issue());
		return "issue/register";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String goToIssueRegister(Issue issue) {
		// TODO Auto-generated method stub
		return "issue/register";
	}
}
