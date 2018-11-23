package com.fdmgroup.Services;

import java.util.ArrayList;

import javax.annotation.Resource;

import com.fdmgroup.DAO.IssueDetailDAO;
import com.fdmgroup.Entities.IssueDetail;

public class IssueDetailService {

	@Resource
	private IssueDetailDAO issueDetailDAO;
	
	public ArrayList<IssueDetail> displayAllDetails(){
		return issueDetailDAO.getIssueDetailsByIssueId(0);
	}
}
