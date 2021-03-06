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

	@Resource(name = "issueDAOBean")
	private IssueDAO issueDao;
	@Resource(name = "issueDetailDAOBean")
	private IssueDetailDAO issueDetailDao;
	@Resource(name = "userDAOBean")
	private UserDAO userDao;
	@Resource(name = "departmentDAOBean")
	private DepartmentDAO departmentDao;
	@Resource(name = "issueBean")
	private Issue issue;
	@Resource(name = "issueDetailBean")
	private IssueDetail issueDetail;
	@Resource(name = "calendarBean")
	private Calendar calendar;

	public String register(String title, String content, String departmentId, String priority, HttpServletRequest req,
			HttpSession session) {
		String result = "issue/register";
		if ("".equals(content)) {
			req.setAttribute("errorMessage", "Content of issue is missing!");
		} else if ("".equals(title)) {
			req.setAttribute("errorMessage", "Title of issue is missing!");
		} else if ("".equals(departmentId)) {
			req.setAttribute("errorMessage", "Department of issue is missing!");
		} else if ("".equals(priority)) {
			req.setAttribute("errorMessage", "Priority of issue is missing!");
		}else {
			issue.setTitle(title);
			issue.setPriority(Integer.parseInt(priority));
			issueDetail.setContent(content);
			Department department = departmentDao.getDepartment(Long.parseLong(departmentId));
			ArrayList<User> depAdmins = userDao.getUserByDep(department);
			User user = userDao.getUser((long)session.getAttribute("userId"));
			issue.setCreateDate(calendar);
			issue.setCreateUser(user);
			issue.setDepartment(department);
			issue.setLastUpdatedDate(calendar);
			issue.setStatus(Status.ASSIGNED);
			issueDetail.setCreateDate(calendar);
			issueDetail.setUser(user);
			issueDetail.setStatus(Status.ASSIGNED);
			issue.addDetail(issueDetail);

			if (!depAdmins.isEmpty()) {
				User takenAdmin = depAdmins.get(0);
				int cases = issueDao.getIssuesByAdminId(takenAdmin).size();
				for (User admin : depAdmins) {
					if (issueDao.getIssuesByAdminId(admin).size() < cases) {
						takenAdmin = admin;
						cases = issueDao.getIssuesByAdminId(admin).size();
					}
				}
				issue.setAdmin(takenAdmin);
			}

			issueDao.addIssue(issue);
			req.setAttribute("message", "Issue created successfully");
			result = "dashboard/customer";
		}
		return result;
	}

	/**
	 * 
	 * @param req
	 */
	public void getAllDepartments(HttpServletRequest req) {
		req.setAttribute("allDepartment", departmentDao.getAllDepartment());
	}
}
