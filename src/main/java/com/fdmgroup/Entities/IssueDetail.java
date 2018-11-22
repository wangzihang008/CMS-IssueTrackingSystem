package com.fdmgroup.Entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fdmgroup.Enum.Status;

@Entity
@Table(name = "ISSUE_DETAILS")
public class IssueDetail {

	@Id
	@Column(name = "ISSUE_DETAIL_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String content;

	private Calendar createDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ISSUE_ID")
	private Issue issue;

	@Enumerated(EnumType.ORDINAL)
	private Status status;

	public IssueDetail(String content, Calendar createDate, User user, Issue issue, Status status) {
		super();
		this.content = content;
		this.createDate = createDate;
		this.user = user;
		this.issue = issue;
		this.status = status;
	}

	public IssueDetail() {
		super();
	}

	@Override
	public String toString() {
		return "IssueDetail [id=" + id + ", content=" + content + ", createDate=" + createDate + ", user=" + user
				+ ", issue=" + issue + ", status=" + status + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Calendar getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
