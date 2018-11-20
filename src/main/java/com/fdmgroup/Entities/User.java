package com.fdmgroup.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fdmgroup.Enum.Status;
import com.fdmgroup.Enum.Type;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue
	private Long id;

	private String username;

	@Column(nullable = false)
	private String password;

	private Type type;

	private Status status;

	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID")
	private Department department;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;
	
	@OneToMany(cascade = CascadeType.ALL)
	@Column(name = "USER_ISSUES")
	private List<Issue> issues;

	public User(Long id, String username, String password, Type type, Status status, Department department,
			String firstName, String lastName, List<Issue> issues) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.type = type;
		this.status = status;
		this.department = department;
		this.firstName = firstName;
		this.lastName = lastName;
		this.issues = issues;
	}

	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

}