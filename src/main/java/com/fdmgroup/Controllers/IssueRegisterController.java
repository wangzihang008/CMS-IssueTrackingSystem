package com.fdmgroup.Controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.IssueDetail;
import com.fdmgroup.Services.IssueRegisterService;

@Controller
@RequestMapping(value="/issue/register")
public class IssueRegisterController {
	
	@Resource(name="customerIssueRegisterBean")
	private IssueRegisterService irs;
	@Resource(name="issueBean")
	private Issue issue;

	@RequestMapping(method=RequestMethod.GET)
	public String goToIssueRegister(Model model, HttpServletRequest req) {
		// TODO Auto-generated method stub
		model.addAttribute("registerIssue", issue);
//		map.addAttribute("newIssueDetail", new IssueDetail());
		irs.getAllDepartments(req);
		return "issue/register";
	}

//	@RequestMapping(method=RequestMethod.POST)
//	public String goToIssueRegister(@ModelAttribute("Issue") Issue issue, HttpServletRequest req, HttpSession session) {
//		// TODO Auto-generated method stub
//		
//		System.out.println("**************" + issue + "********************");
//		String forward = irs.register(issue, req, session);
//		
//		return forward;
//	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String goToIssueRegister(@RequestParam String title, @RequestParam String content,
			@RequestParam String department, @RequestParam String priority, HttpServletRequest req, HttpSession session) {
		String forward = irs.register(title, content, department, priority, req, session);
		return forward;
	}
}
