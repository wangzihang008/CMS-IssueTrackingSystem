package com.fdmgroup.Services;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.DAO.IssueDetailDAO;
import com.fdmgroup.Entities.IssueDetail;

public class IssueUpdateCustomerService {

	@Resource(name="issueDAOBean")
	private IssueDAO issueDao;
	@Resource(name="issueDetailDAOBean")
	private IssueDetailDAO issueDetailDao;
	
	public String update(String issueId, IssueDetail issueDetail, HttpServletRequest req, HttpSession session) {
		
		return null;
	}

}

