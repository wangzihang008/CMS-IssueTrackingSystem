package com.fdmgroup.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DEPARTMENTS")
public class Department {

	@Id
	@Column(name = "DEPARTMENT_ID")
	@GeneratedValue
	private Long id;

	@Column(name = "DEPARTMENT_NAME")
	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	@Column(name = "LIST_ADMINS")
	private List<User> admins = new ArrayList<>();

	public Department(Long id, String name, List<User> admins) {
		super();
		this.id = id;
		this.name = name;
		this.admins = admins;
	}

	public Department() {
		super();
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", admins=" + admins + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getAdmins() {
		return admins;
	}

	public void setAdmins(List<User> admins) {
		this.admins = admins;
	}

}