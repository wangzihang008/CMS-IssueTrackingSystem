package com.fdmgroup.Services;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.DAO.IssueDetailDAO;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.IssueDetail;

public class IssueRegisterService {
	
	@Resource(name="issueDAOBean")
	private IssueDAO issueDao;
	@Resource(name="issueDetailDAOBean")
	private IssueDetailDAO issueDetailDao;
	
	public void register(IssueDetail issueDetail, HttpServletRequest req) {
		Issue issue = new Issue();
	}
}
