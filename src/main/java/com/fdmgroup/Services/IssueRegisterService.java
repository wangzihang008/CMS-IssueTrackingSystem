package com.fdmgroup.Services;

import javax.annotation.Resource;

import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.DAO.IssueDetailDAO;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.IssueDetail;

public class IssueRegisterService {
	
	@Resource(name="issueDAOBean")
	private IssueDAO issueDao;
	@Resource(name="issueDetailDAOBean")
	private IssueDetailDAO issueDetailDao;
	
	public void register(Issue issue, IssueDetail issueDetail) {
		
	}
}
