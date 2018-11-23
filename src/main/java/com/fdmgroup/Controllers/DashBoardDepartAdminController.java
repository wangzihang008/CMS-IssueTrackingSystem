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
import com.fdmgroup.DAO.IssueDetailDAO;
import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.Department;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.IssueDetail;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Status;
import com.fdmgroup.Enum.Type;


@Controller
@SessionAttributes(value = {"blank_user_login", "active_issue"})
public class DashBoardDepartAdminController {
	
	@Resource(name = "issueDAOBean")
	private IssueDAO iDao;
	
	@Resource(name = "userDAOBean")
	private UserDAO uDao;

	@Resource(name = "issueDetailDAOBean")
	private IssueDetailDAO idDao;
	
	@RequestMapping(value= "/dashboard/depadmin", method=RequestMethod.GET)
	public String goToDepartAdminDashBoard() {
		// TODO Auto-generated method stub	
		return "dashboard/depadmin";
	}
	
	
	@RequestMapping(value= "/dashboard/depadmin", method = RequestMethod.POST)
	public String checkIssues(Model model, Issue issue, @ModelAttribute(value = "blank_user_login") User user) {
		
		if (issue.getTitle().equals("")){
			
			User loggedInInUser = uDao.get(user.getUsername());
			model.addAttribute("active_user", loggedInInUser.getUsername());
			List<Issue> issues = iDao.getIssuesByDepartment(loggedInInUser.getDepartment());	
			model.addAttribute("issues", issues);
			return "dashboard/depadmin";
		
		}else {
			
			
			//issue.setId(generateId(issue));
			Issue accessedIssue = iDao.getIssue(generateId(issue));
			model.addAttribute("active_issue", accessedIssue);
			List<IssueDetail> details = idDao.getIssueDetailsByIssue(accessedIssue);
			model.addAttribute("details", details);
			return "issue/update/depadmin";
		}	
		
	}
	
	private int generateId(Issue issue) {
		
		int start = issue.getTitle().indexOf(":");
		start += 2;
		int end = issue.getTitle().indexOf(",");
		String idStr = issue.getTitle().substring(start, end);
		int id = Integer.parseInt(idStr);
		
		return id;
	}
	

}
