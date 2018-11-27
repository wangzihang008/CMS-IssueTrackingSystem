package com.fdmgroup.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DEPARTMENTS")
public class Department {

	@Id
	@Column(name = "DEPARTMENT_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "DEPARTMENT_NAME")
	private String name;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@Column(name = "LIST_ADMINS")
	private List<User> admins = new ArrayList<>();

	public Department(String name) {
		super();
		this.name = name;
	}

	public Department() {
		super();
	}


	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", admins=" + admins + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
	
	public void addAdmin(User admin) {
		admins.add(admin);
		admin.setDepartment(this);
	}

	public void removeAdmin(User admin) {
		admins.remove(admin);
		admin.setDepartment(null);
	}

}
