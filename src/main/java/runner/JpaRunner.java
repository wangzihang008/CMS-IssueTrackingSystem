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
		/*
		Department d1 = new Department("marketing", null);
		DepartmentDAO dDao = new DepartmentDAO(emf);
		dDao.addDepartment(d1);
		
		User u1 = new User("qz", "qzz", Type.DEPARTMENT_ADMIN, Status.UNASSIGNED, d1,
				"q", "z", null);
		UserDAO uDao = new UserDAO(emf);
		uDao.addUser(u1);
		
		Department d2 = new Department("tech", null);
		Department d3 = new Department("general", null);

		
		dDao.addDepartment(d2);
		dDao.addDepartment(d3);
		*/
		IssueDAO iDao = new IssueDAO(emf);
		IssueDetailDAO idDao = new IssueDetailDAO(emf);
		UserDAO uDao = new UserDAO(emf);
		Issue i = iDao.getIssue(23);
		User u = uDao.get("qz");
		//IssueDetail id = new IssueDetail("ccc", Calendar.getInstance(), u, i, null);
		System.out.println(idDao.getIssueDetailsByIssue(i));
		//iDao.addDetail(i, "dddd", u);
		//Issue i1 = new Issue("aaaaaaa", Status.ASSIGNED, Calendar.getInstance(), Calendar.getInstance(), null,
		//		null, d1, null);
		
		//Issue i2 = new Issue("bbb", Status.UNASSIGNED, Calendar.getInstance(), Calendar.getInstance(), null,
		//		null, d1, null);
		/*
		Issue i3 = new Issue("ccc", Status.UNASSIGNED, Calendar.getInstance(), Calendar.getInstance(), null,
				null, d3, null);
		*/
		//EntityManager em = emf.createEntityManager();
			
		//iDao.addIssue(i1);
		
		//iDao.addIssue(i2);
		/*
		iDao.addIssue(i3);
		*/
		//String t = "aaa";
		//Query query = em.createQuery(
		//		"SELECT title FROM Issue WHERE title = '" +t+ "'", String.class);
		//List<String> issues = iDao.getIssuesByDepartment(d1);
		//System.out.println(issues);
		
	}

}
