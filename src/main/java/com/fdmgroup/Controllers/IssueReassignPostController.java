package com.fdmgroup.Controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.Services.IssueReassignService;

@Controller
@RequestMapping(value = "/issue/reassign")
public class IssueReassignPostController {

	@Resource(name="issueReassignServiceBean")
	private IssueReassignService irs;
	
	@RequestMapping(method = RequestMethod.POST)
	public String goToDashboard(@ModelAttribute String issueId, String department, Model model) {
		irs.reassign(Long.parseLong(issueId), department);
		model.addAttribute("reassignMsg", "Reassign success. See updated dashboard");
		return "dashboard/admin";
	}
}
