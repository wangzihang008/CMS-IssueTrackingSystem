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

@Controller
@SessionAttributes(value = {"active_issue"})
public class AddNewDetailDepartAdminController {

	@Resource(name = "issueDAOBean")
	private IssueDAO iDao;
	
	@Resource(name = "userDAOBean")
	private UserDAO uDao;
	
	@Resource(name = "issueDetailDAOBean")
	private IssueDetailDAO idDao;
	
	@RequestMapping(value= "/issue/addDetailDepartAdmin", method=RequestMethod.GET)
	public String goToAddDetail(Model model) {
		// TODO Auto-generated method stub
		model.addAttribute("blank_detail", new IssueDetail());
		return "/issue/addDetailDepartAdmin";
	}
	
	@RequestMapping(value= "/issue/addDetailDepartAdmin", method=RequestMethod.POST)
	public String addDetail(Model model, @ModelAttribute(value = "active_issue") Issue issue, IssueDetail detail, HttpSession session) {
		// TODO Auto-generated method stub	
		model.addAttribute("blank_detail", detail);
		String name = (String) session.getAttribute("userName");
		User user = uDao.get(name);
		detail.setUser(user);
		detail.setIssue(issue);
		detail.setCreateDate(Calendar.getInstance());
		idDao.addIssueDetail(detail);
		model.addAttribute("active_user", user.getUsername());
		List<Issue> issues = iDao.getAssignedIssuesByDepartment(user.getDepartment());
		model.addAttribute("issues", issues);
		model.addAttribute("msg", "You just added a new detail to " + issue);
		
		return "/dashboard/depadmin";
		
	}
}
