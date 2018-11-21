package com.fdmgroup.DAO;

import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdmgroup.Entities.Department;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Enum.Status;
import com.fdmgroup.Enum.Type;

public class App {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EMF");

		UserDAO uDAO = new UserDAO(emf);
		DepartmentDAO d = new DepartmentDAO(emf);

		Department tech = new Department("Techonology", new ArrayList<User>());
		User u1 = new User("jimmy66666", "welcome123", Type.BASIC_USER, Status.ASSIGNED, tech, "Jimmy", "Zhou",
				new ArrayList<Issue>());
		d.addDepartment(tech);
		uDAO.addUser(u1);

	}
}
