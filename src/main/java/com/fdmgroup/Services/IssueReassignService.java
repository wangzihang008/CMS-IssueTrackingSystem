package com.fdmgroup.Services;

import java.util.ArrayList;
import java.util.Random;

import javax.annotation.Resource;

import com.fdmgroup.DAO.DepartmentDAO;
import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.Department;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.User;

public class IssueReassignService {

	@Resource(name = "issueDAOBean")
	private IssueDAO issueDAO;

	@Resource(name = "userDAOBean")
	private UserDAO userDAO;

	@Resource(name = "departmentDAOBean")
	private DepartmentDAO departmentDAO;

	public void reassign(long issueId, String departmentName) {
		Issue issue = issueDAO.getIssue(issueId);
		Department department = departmentDAO.getDepartment(departmentName);
		ArrayList<User> admins = userDAO.getUserByDep(department);
		Random rand = new Random();
		User admin = admins.get(rand.nextInt(admins.size()));
		issueDAO.reassignHelper(issue, department, admin);
	}
}
