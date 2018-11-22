package com.fdmgroup.DAO;

import java.util.Calendar;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdmgroup.Entities.Department;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.IssueDetail;
import com.fdmgroup.Enum.Status;
import com.fdmgroup.Enum.Type;

public class App {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EMF");

		UserDAO uDAO = new UserDAO(emf);
		DepartmentDAO dDAO = new DepartmentDAO(emf);
		IssueDAO iDAO = new IssueDAO(emf);
		IssueDetailDAO idDAO = new IssueDetailDAO(emf);

		Department tech = new Department("Techonology");
		User u1 = new User("jimmy66666", "welcome123", Type.CUSTOMER, Status.ACTIVE, tech, "Jimmy", "Zhou");
		User u2 = new User("test", "testtest", Type.ADMIN, Status.ACTIVE, tech, "Darren", "Ng");
		Issue i1 = new Issue("VIP", Status.ASSIGNED, Calendar.getInstance(), 1, u2, u1, tech);
		IssueDetail id = new IssueDetail("HAHA", Calendar.getInstance(), u1, i1);
		
		dDAO.addDepartment(tech);
		uDAO.addUser(u1);
		iDAO.addIssue(i1);
		idDAO.addIssueDetail(id);
		dDAO.getDepartment(1);
		System.out.println(u1.getType().equals(Type.CUSTOMER));

	}
}
