package com.fdmgroup.Controllers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdmgroup.DAO.DepartmentDAO;
import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.Department;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Type;

public class Runner {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EMF");
		UserDAO ud = new UserDAO(emf);
		DepartmentDAO dd = new DepartmentDAO(emf);
		User user = new User();
		user.setUsername("robertwang");
		user.setPassword("rw1234");
		user.setType(Type.CUSTOMER);
		User admin = new User();
		admin.setUsername("robertwangadmin");
		admin.setPassword("rw1234");
		admin.setType(Type.ADMIN);
		Department department = new Department("Dev");
		
		ud.addUser(user);
		ud.addUser(admin);
		dd.addDepartment(department);
		
		System.out.println(ud.get("robertwang"));
	}
}
