package com.fdmgroup.Controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.Enum.Type;
import com.fdmgroup.Services.AdminDashboardService;

@Controller
public class AdminDashboardController {
	@Resource(name="adminDashboardServiceBean")
	private AdminDashboardService ads;
	
	@RequestMapping(value="dashboard/admin", method=RequestMethod.GET)
	public String goToAdminDashboard(HttpSession session, Model model) {
		// TODO Auto-generated method stub
		if(session.getAttribute("userType").equals(Type.GENERAL_ADMIN)) {
			if(!ads.getAllIssues().isEmpty()) {
				model.addAttribute("issues", ads.getAllIssues());
			}
		}
			
		return "dashboard/admin";
	}

}