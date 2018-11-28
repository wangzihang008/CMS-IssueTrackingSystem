package com.fdmgroup.Controllers;

import java.util.Calendar;
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
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.IssueDetail;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Status;

@Controller
@SessionAttributes(value = {"active_issue"})
public class ResolveController {

	@Resource(name = "issueDAOBean")
	private IssueDAO iDao;
	
	@Resource(name = "userDAOBean")
	private UserDAO uDao;
	
	@Resource(name = "issueDetailDAOBean")
	private IssueDetailDAO idDao;
	
	@Resource(name = "calendarBean")
	private Calendar calendar;
	
	@RequestMapping(value= "/issue/resolve", method=RequestMethod.GET)
	public String goToResolve() {
		// TODO Auto-generated method stub
		return "/issue/resolve";
	}
	
	@RequestMapping(value= "/issue/resolve", method=RequestMethod.POST)
	public String markResolve(Model model, @ModelAttribute(value = "active_issue") Issue issue, int selection, HttpSession session) {
		// TODO Auto-generated method stub	
		String name = (String) session.getAttribute("userName");
		User user = uDao.get(name);
		if(selection == 1) {
			
			iDao.changeStatus(issue, Status.RESOLVED);
			idDao.addIssueDetail(new IssueDetail(user.getUsername() + " marked this issue resolved", calendar, user, issue));
			model.addAttribute("active_user", user.getUsername());
			List<Issue> issues = iDao.getAssignedIssuesByDepartment(user.getDepartment());
			model.addAttribute("issues", issues);
			model.addAttribute("msg", "You have successfully marked " + issue + " resolved");
			return "/dashboard/depadmin";			
		
		}else if(selection == 0) {
			
			model.addAttribute("active_user", user.getUsername());
			List<Issue> issues = iDao.getAssignedIssuesByDepartment(user.getDepartment());
			model.addAttribute("issues", issues);
			return "/dashboard/depadmin";
			
		}else {
			
			model.addAttribute("msg", "please select yes or no");
			return "/issue/resolve";
		}
		
	}
}
