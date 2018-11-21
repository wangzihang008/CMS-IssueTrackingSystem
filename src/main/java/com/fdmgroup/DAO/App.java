package com.fdmgroup.DAO;

import java.util.ArrayList;
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

		Department tech = new Department("Techonology", new ArrayList<User>());
		User u1 = new User("jimmy66666", "welcome123", Type.BASIC_USER, Status.ASSIGNED, tech, "Jimmy", "Zhou",
				new ArrayList<Issue>());
		Issue i1 = new Issue("VIP", Status.UNASSIGNED, Calendar.getInstance(), 1, Calendar.getInstance(), u1, u1, tech, new ArrayList<IssueDetail>());
		IssueDetail id = new IssueDetail("HAHA", Calendar.getInstance(), u1, i1, Status.RESOLVED);
		User u2 = new User("jimmy66666", "welcome123", Type.BASIC_USER, Status.ASSIGNED, tech, "Jimmy", "Zhou",
				new ArrayList<Issue>());
		
		dDAO.addDepartment(tech);
		uDAO.addUser(u1);
		iDAO.addIssue(i1);
		idDAO.addIssueDetail(id);
		dDAO.getDepartment(1);
		System.out.println(u1.getType().equals(Type.BASIC_USER));

	}
}
