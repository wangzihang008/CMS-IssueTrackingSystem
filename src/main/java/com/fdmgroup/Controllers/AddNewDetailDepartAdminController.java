package com.fdmgroup.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AddNewDetailDepartAdminController {

	@RequestMapping(value= "/issue/update/addNewIssueDetailDepartAdmin", method=RequestMethod.GET)
	public String goToAdd() {
		// TODO Auto-generated method stub
		return "/issue/update/addNewIssueDetailDepartAdmin";
	}
}
