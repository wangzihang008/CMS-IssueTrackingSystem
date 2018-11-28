package com.fdmgroup.Entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fdmgroup.Enum.Status;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name = "ISSUES")
public class Issue {

	@Id
	@Column(name = "ISSUE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String title;

	@Enumerated(EnumType.ORDINAL)
	private Status status;

	private Calendar createDate;

	private int priority;

	private Calendar lastUpdatedDate;

	@ManyToOne
	@JoinColumn(name = "ADMIN_ID")
	private User admin;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User createUser;

	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID")
	private Department department;

	@OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
	@Column(name = "USER_ISSUE_DETAILS")
	private List<IssueDetail> details = new ArrayList<>();

	public Issue(String title, Status status, Calendar createDate, int priority, Calendar lastUpdatedDate, User admin,
			User createUser, Department department) {
		super();
		this.title = title;
		this.status = status;
		this.createDate = createDate;
		this.priority = priority;
		this.lastUpdatedDate = lastUpdatedDate;
		this.admin = admin;
		this.createUser = createUser;
		this.department = department;
	}

	public Issue() {
		super();
	}

	@Override
	public String toString() {

		return "Issue id: "+ id + ", " + title;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Calendar getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}

	public Calendar getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Calendar lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<IssueDetail> getDetails() {
		return details;
	}

	public void setDetails(List<IssueDetail> details) {
		this.details = details;
	}

	public void addDetail(IssueDetail detail) {
		details.add(detail);
		detail.setIssue(this);
	}

	public void removeDetail(IssueDetail detail) {
		details.remove(detail);
		detail.setIssue(null);
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
