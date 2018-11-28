package runner;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.fdmgroup.DAO.DepartmentDAO;
import com.fdmgroup.DAO.IssueDAO;
import com.fdmgroup.DAO.IssueDetailDAO;
import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.Department;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.IssueDetail;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Status;
import com.fdmgroup.Enum.Type;

public class JpaRunner {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EMF");

		
		Department d1 = new Department("Development");
		Department d2 = new Department("Support");
		DepartmentDAO dDao = new DepartmentDAO(emf);
		dDao.addDepartment(d1);
		dDao.addDepartment(d2);
		
		User u1 = new User("abc", "abc", Type.DEPADMIN, Status.ACTIVE, d1, "Chris", "Brown");
		User u2 = new User("abcd", "abcd", Type.DEPADMIN, Status.ACTIVE, d2, "JK", "Rowling");
		User u3 = new User("abcde", "abcde", Type.ADMIN, Status.ACTIVE, null, "Warren", "G");
		User u4 = new User("abcdef", "abcdef", Type.ADMIN, Status.ACTIVE, null, "Jimmy", "Butler");
		User u5 = new User("abcdeg", "abcdefg", Type.ADMIN, Status.ACTIVE, null, "Kobe", "Byrant");
		UserDAO uDao = new UserDAO(emf);
		uDao.addUser(u1);
		uDao.addUser(u2);
		uDao.addUser(u3);
		uDao.addUser(u4);
		uDao.addUser(u5);
		


		
		
		

	}

}
