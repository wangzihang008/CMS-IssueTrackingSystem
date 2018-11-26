package com.fdmgroup.Runner;

import java.util.Calendar;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdmgroup.DAO.DepartmentDAO;
import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.DAO.IssueDetailDAO;
import com.fdmgroup.DAO.UserDAO;
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
		User u1 = new User("test", "testtest", Type.DEPADMIN, Status.ACTIVE, tech, "Chris", "Spencer");
		User u2 = new User("darren", "darren123", Type.ADMIN, Status.ACTIVE, tech, "Darren", "Ng");
		Issue i1 = new Issue("VIP", Status.ASSIGNED, Calendar.getInstance(), 1, u2, u1, tech);
		IssueDetail id = new IssueDetail("HAHA", Calendar.getInstance(), u1, i1);
		
		dDAO.addDepartment(tech);
		uDAO.addUser(u1);
		uDAO.addUser(u2);
		iDAO.addIssue(i1);
		idDAO.addIssueDetail(id);

	}
}
