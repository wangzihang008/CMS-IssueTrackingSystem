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
@SessionAttributes(value = "blank_user_login")
public class DashBoardDepartAdminController {
	
	@Resource(name = "issueDAOBean")
	private IssueDAO iDao;
	
	@Resource(name = "userDAOBean")
	private UserDAO uDao;
	
	@RequestMapping(value= "/dashboard/depadmin", method=RequestMethod.GET)
	public String goToDepartAdminDashBoard() {
		// TODO Auto-generated method stub	
		return "dashboard/depadmin";
	}
	
	
	@RequestMapping(value= "/dashboard/depadmin", method = RequestMethod.POST)
	public String manageIssues(Model model, Issue issue, @ModelAttribute(value = "blank_user_login") User user) {
		
		if (issue.getTitle().equals("")){
			
			User loggedInInUser = uDao.get(user.getUsername());
			model.addAttribute("active_user", loggedInInUser.getUsername());
			List<String> issues = iDao.getIssuesByDepartment(loggedInInUser.getDepartment());	
			model.addAttribute("issues", issues);
			return "dashboard/depadmin";
		
		}else {
		
			return "issueManagementDepartAdmin";
		}	
		
	}
	

}
