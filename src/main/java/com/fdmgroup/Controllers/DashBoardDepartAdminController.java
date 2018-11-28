package com.fdmgroup.Controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
@SessionAttributes(value = {"active_issue"})
public class DashBoardDepartAdminController {
	
	@Resource(name = "issueDAOBean")
	private IssueDAO iDao;
	
	@Resource(name = "userDAOBean")
	private UserDAO uDao;

	@Resource(name = "issueDetailDAOBean")
	private IssueDetailDAO idDao;
	
	@RequestMapping(value= "/dashboard/depadmin", method=RequestMethod.GET)
	public String goToDepartAdminDashBoard(Model model, HttpSession session) {
		// TODO Auto-generated method stub	
		String username = (String) session.getAttribute("userName");
		User loggedInInUser = uDao.get(username);
		model.addAttribute("active_user", loggedInInUser.getUsername());
		List<Issue> issues = iDao.getAssignedIssuesByDepartment(loggedInInUser.getDepartment());	
		model.addAttribute("issues", issues);
		return "dashboard/depadmin";
	}
	
	
	@RequestMapping(value= "/dashboard/depadmin", method = RequestMethod.POST)
	public String checkIssues(Model model, Issue issue, HttpSession session) {
		
		if (issue.getTitle().equals("")){
			
			String username = (String) session.getAttribute("userName");
			User loggedInInUser = uDao.get(username);
			model.addAttribute("active_user", loggedInInUser.getUsername());
			List<Issue> issues = iDao.getAssignedIssuesByDepartment(loggedInInUser.getDepartment());	
			model.addAttribute("issues", issues);
			model.addAttribute("select_msg", "please select an issue");
			return "dashboard/depadmin";
		
		}else {
			
			
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
