package com.fdmgroup.Controllers;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Enum.Type;
import com.fdmgroup.Services.AdminDashboardService;

@Controller
public class AdminDashboardController {
	@Resource(name="adminDashboardServiceBean")
	private AdminDashboardService ads;
	
	@RequestMapping(value="dashboard/admin", method=RequestMethod.GET)
	public String goToAdminDashboard(HttpServletRequest req, HttpSession session, Model model) {
		// TODO Auto-generated method stub
		if(session.getAttribute("userType").equals(Type.ADMIN)) {
			ArrayList<Issue> issues = ads.getAllIssues();
			if(!issues.isEmpty()) {
				model.addAttribute("issues", issues);
			}
		}else {
			req.setAttribute("errorMessage", "You are not an admin user!");
			return "/index";
		}
			
		return "dashboard/admin";
	}

}
