package com.fdmgroup.Services;

import java.util.ArrayList;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fdmgroup.DAO.DepartmentDAO;
import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.DAO.IssueDetailDAO;
import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.Department;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.IssueDetail;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Status;

public class IssueRegisterService {
	
	@Resource(name="issueDAOBean")
	private IssueDAO issueDao;
	@Resource(name="issueDetailDAOBean")
	private IssueDetailDAO issueDetailDao;
	@Resource(name="userDAOBean")
	private UserDAO userDao;
	@Resource(name="departmentDAOBean")
	private DepartmentDAO departmentDao;
	
	public void register(IssueDetail issueDetail, HttpServletRequest req, HttpSession session) {
		if("".equals(issueDetail.getContent())) {
			req.setAttribute("errorMessage", "Content of issue is missing!");
		}else if("".equals(req.getAttribute("title"))){
			req.setAttribute("errorMessage", "Title of issue is missing!");
		}else if("".equals(req.getAttribute("department"))) {
			req.setAttribute("errorMessage", "Department of issue is missing!");
		}else if("".equals(req.getAttribute("priority"))){
			req.setAttribute("errorMessage", "priority of issue is missing!");
		}else {
			Issue issue = new Issue();
			Calendar calendar = Calendar.getInstance();
			Department department = departmentDao.getDepartment((String)req.getAttribute("department"));
			ArrayList<User> depAdmins = userDao.getUserByDep(department.getId());
			User user = userDao.getUser((long)session.getAttribute("userId"));
			issue.setCreateDate(calendar);
			issue.setCreateUser(user);
			issue.setDepartment(department);
			issue.setLastUpdatedDate(calendar);
			issue.setStatus(Status.ASSIGNED);
			issue.setTitle((String)req.getAttribute("title"));
			issue.setPriority((int)req.getAttribute("priority"));
			
			issueDetail.setCreateDate(calendar);
			issueDetail.setUser(user);
			issue.addDetail(issueDetail);
			
			User takenAdmin = depAdmins.get(0);
			int cases = issueDao.getIssuesByAdminId(takenAdmin.getId()).size();
			for(User admin : depAdmins) {
				if(issueDao.getIssuesByAdminId(admin.getId()).size() < cases) {
					takenAdmin = admin;
					cases = issueDao.getIssuesByAdminId(admin.getId()).size();
				}
			}
			
			issue.setAdmin(takenAdmin);
			issueDao.addIssue(issue);
		}
	}
	
	public void getAllDepartments(HttpServletRequest req) {
		req.setAttribute("allDepartment", departmentDao.getAllDepartment());
	}
}
