package com.fdmgroup.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AddDetailDepartAdminController {
	
	@RequestMapping(value= "/issue/add", method=RequestMethod.GET)
	public String goToAddDetail() {
		// TODO Auto-generated method stub
		return "/issue/add";
	}
	
/*	@RequestMapping(value= "/issue/addDetailDepartAdmin", method=RequestMethod.POST)
	public String addDetail() {
		
		return "index";
	}*/
}
