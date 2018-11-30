package com.fdmgroup.Controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.DAO.DepartmentDAO;
import com.fdmgroup.Services.IssueReassignService;

@Controller
@RequestMapping(value = "/issue/reassign/{issueId}")
public class IssueReassignController {
	
	@Resource(name="issueReassignServiceBean")
	private IssueReassignService irs;
	
	@Resource(name="departmentDAOBean")
	private DepartmentDAO departmentDAO;

	@RequestMapping(method = RequestMethod.GET)
	public String goToIssueReassign(Model model) {
		model.addAttribute("allDepartment", departmentDAO.getAllDepartment());
		return "dashboard/admin";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String goToDashboard(@PathVariable long issueId, @RequestParam String department, Model model) {
		irs.reassign(issueId, department);
		model.addAttribute("reassignMsg", "Reassign success. See updated dashboard");
		return "dashboard/admin";
	}
}
