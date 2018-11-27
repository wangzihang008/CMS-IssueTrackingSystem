package com.fdmgroup.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/IssueTrackingSystem/issue/reassign")
public class IssueReassignController {

	@RequestMapping(method = RequestMethod.GET)
	public String goToIssueReassign() {
		return "/IssueTrackingSystem/issue/reassign";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String goToDashboard(Model model) {
		model.addAttribute("reassignMsg", "Reassign success. See updated dashboard");
		return "/IssueTrackingSystem/dashboard/admin";
	}
}
