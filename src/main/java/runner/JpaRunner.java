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
		IssueDAO iDao = new IssueDAO(emf);
		UserDAO uDao = new UserDAO(emf);
		IssueDetailDAO idDao = new IssueDetailDAO(emf);
		DepartmentDAO dDao = new DepartmentDAO(emf);
		Issue i1 = iDao.getIssue(2);
		iDao.changeStatus(i1, Status.ASSIGNED);
	}

}
