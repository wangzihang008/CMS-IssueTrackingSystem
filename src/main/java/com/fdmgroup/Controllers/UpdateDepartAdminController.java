package com.fdmgroup.Controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.Entities.Issue;

@Controller
@SessionAttributes(value = {"blank_user_login", "active_issue"})
public class UpdateDepartAdminController {

	@Resource(name = "issueDAOBean")
	private IssueDAO iDao;
	
	@RequestMapping(value= "/issue/update/depadmin", method=RequestMethod.GET)
	public String goToDepartAdminIssueUpdate() {
		// TODO Auto-generated method stub	
		return "issue/update/depadmin";
	}
	
	@RequestMapping(value= "/issue/update/depadmin", method = RequestMethod.POST)
	public String updateIssues(Model model, @ModelAttribute(value = "active_issue")Issue issue) {
		
		return null;
	}
}
