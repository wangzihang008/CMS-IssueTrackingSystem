package com.fdmgroup.Services;

import java.util.ArrayList;

import javax.annotation.Resource;

import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.Entities.Issue;

public class AdminDashboardService {
	@Resource(name="issueDAOBean")
	private IssueDAO issueDao;
	
	/**
	 * 
	 * @return All issues in database
	 */
	public ArrayList<Issue> getAllIssues(){
		return issueDao.getAllIssue();
	}
}
