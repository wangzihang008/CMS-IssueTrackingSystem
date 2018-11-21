package com.fdmgroup.DAO;

import java.util.ArrayList;
import com.fdmgroup.Entities.Department;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Enum.Status;
import com.fdmgroup.Enum.Type;

public class App {

	public static void main(String[] args) {

		Department newDepartment = new Department(12L, "Techonology", new ArrayList<User>());
		User newUser = new User(100L, "jimmy66666", "welcome123", Type.BASIC_USER, Status.ASSIGNED, newDepartment, "Jimmy", "Zhou", new ArrayList<Issue>());
	}
}
