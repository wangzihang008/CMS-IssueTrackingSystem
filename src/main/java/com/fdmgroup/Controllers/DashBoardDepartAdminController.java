package com.fdmgroup.Controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.Department;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Status;
import com.fdmgroup.Enum.Type;


@Controller
//@SessionAttributes(value = "login_user")
public class DashBoardDepartAdminController {
	
	@Resource(name = "issueDAOBean")
	private IssueDAO iDao;
	
	@Resource(name = "userDAOBean")
	private UserDAO uDao;
	
	@RequestMapping(value= "/dashboardDepartAdmin", method=RequestMethod.GET)
	public String goToDepartAdminDashBoard(Model model) {//, @ModelAttribute(value = "login_user") User user) {
		// TODO Auto-generated method stub
		User loggedInInUser = uDao.getUser(22);
		model.addAttribute("active_user", loggedInInUser.getUsername());
		List<String> issues = iDao.getIssuesByDepartment(loggedInInUser.getDepartment());	
		model.addAttribute("issues", issues);
		return "dashboardDepartAdmin";
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String manageIssues(Model model, Issue issue) {
		
		if (issue.getTitle().equals("")){
			
			return "dashboardDepartAdmin";
		
		}else {
		
			return "issueManagementDepartAdmin";
		}	
		
	}
	

}
