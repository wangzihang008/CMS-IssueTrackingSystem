package com.fdmgroup.Controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.DAO.IssueDetailDAO;
import com.fdmgroup.Entities.IssueDetail;

@Controller
@RequestMapping(value = "/issue/update/depadmin")
public class IssueUpdateDepartAdminController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String goToIssueUpdateDepartAdmin() {
		
		return "/issue/update/depadmin";
	}
	
}
