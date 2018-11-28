package com.fdmgroup.Controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.Entities.IssueDetail;
import com.fdmgroup.Services.IssueUpdateCustomerService;

@Controller
public class IssueUpdateCustomerController {
	
	@Resource(name="IssueUpdateCustomerServiceBean")
	private IssueUpdateCustomerService iucs;
	
	@RequestMapping(value= {"/issue/update/customer/{issueId}"}, method=RequestMethod.GET)
	public String goToUpdate(Model model, HttpServletRequest req, HttpSession session,
			@PathVariable String issueId) {
		String forward = iucs.check(issueId, model, req, session);
		return forward;
	}
	
	@RequestMapping(value="/issue/update/customer/{issueId}", method=RequestMethod.POST)
	public String goToUpdate(HttpServletRequest req, HttpSession session, 
			IssueDetail issueDetail, @PathVariable String issueId) {
		String forward = iucs.update(issueId, issueDetail, req, session);
		return forward;
	}
}
