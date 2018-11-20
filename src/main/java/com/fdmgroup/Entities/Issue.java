package com.fdmgroup.Entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
	@GeneratedValue
	private Long id;

	private String title;

	private Status status;

	private Calendar createDate;

	private Calendar lastUpdatedDate;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
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

	public Issue(Long id, String title, Status status, Calendar createDate, Calendar lastUpdatedDate, User admin,
			User createUser, Department department, List<IssueDetail> details) {
		super();
		this.id = id;
		this.title = title;
		this.status = status;
		this.createDate = createDate;
		this.lastUpdatedDate = lastUpdatedDate;
		this.admin = admin;
		this.createUser = createUser;
		this.department = department;
		this.details = details;
	}

	public Issue() {
		super();
	}

	@Override
	public String toString() {
		return "Issue [id=" + id + ", title=" + title + ", status=" + status + ", createDate=" + createDate
				+ ", lastUpdatedDate=" + lastUpdatedDate + ", admin=" + admin + ", createUser=" + createUser
				+ ", department=" + department + ", details=" + details + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

}
