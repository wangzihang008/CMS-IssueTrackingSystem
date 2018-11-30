package com.fdmgroup.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/issue/update/depadmin")
public class IssueUpdateDepartAdminController {

	@RequestMapping(method = RequestMethod.GET)
	public String goToIssueUpdateDepartAdmin() {

		return "/issue/update/depadmin";
	}
}
