package com.fdmgroup.Services;

import java.util.ArrayList;

import javax.annotation.Resource;

import com.fdmgroup.DAO.IssueDetailDAO;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.IssueDetail;

public class IssueDetailService {

	@Resource(name = "issueDetailDAOBean")
	private IssueDetailDAO issueDetailDAO;

	public ArrayList<IssueDetail> displayAllDetails(Issue issue) {
		return issueDetailDAO.getIssueDetailsByIssue(issue);
	}
}
