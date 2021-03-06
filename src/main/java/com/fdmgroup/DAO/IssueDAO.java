package com.fdmgroup.DAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

import com.fdmgroup.Entities.Department;
import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.IssueDetail;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Status;

public class IssueDAO {

	@Resource(name = "emfBean")
	private EntityManagerFactory emf;

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public IssueDAO(EntityManagerFactory emf) {

		this.emf = emf;
	}

	public IssueDAO() {

	}

	public void addIssue(Issue issue) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(issue);
		et.commit();
		em.close();
	}

	public Issue getIssue(long id) {
		EntityManager em = emf.createEntityManager();
		Issue returnedIssue = em.find(Issue.class, id);
		em.close();
		return returnedIssue;
	}

	/**
	 * 
	 * @return All Issues In DataBase
	 */
	public ArrayList<Issue> getAllIssue() {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		String str = "select i from Issue i";
		TypedQuery<Issue> query = (TypedQuery<Issue>) em.createQuery(str);
		ArrayList<Issue> result = (ArrayList<Issue>) query.getResultList();
		em.close();
		return result;
	}

	/**
	 * 
	 * @param adminId
	 * @return a list of issue, given admin id
	 */
	public ArrayList<Issue> getIssuesByAdminId(User admin) {
		EntityManager em = emf.createEntityManager();

		String str = "select i from Issue i WHERE i.admin=:admin";
		TypedQuery<Issue> query = (TypedQuery<Issue>) em.createQuery(str);
		query.setParameter("admin", admin);
		ArrayList<Issue> result = (ArrayList<Issue>) query.getResultList();
		em.close();
		return result;
	}

	public ArrayList<Issue> getIssuesByUserId(long userId) {
		EntityManager em = emf.createEntityManager();

		String str = "select i from Issue i WHERE user_id = " + userId;
		TypedQuery<Issue> query = (TypedQuery<Issue>) em.createQuery(str);
		ArrayList<Issue> result = (ArrayList<Issue>) query.getResultList();
		em.close();
		return result;
	}
	
	public List<Issue> getAssignedIssuesByDepartment(Department department) {

		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery(
				"SELECT i FROM Issue i WHERE department_id = '" + department.getId() + "' AND status = 1", Issue.class);
		List<Issue> issues = query.getResultList();
		return issues;
	}

	public void update(Issue issue) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		Issue issue1 = em.find(Issue.class, issue.getId());
		et.begin();
		issue1.setAdmin(issue.getAdmin());
		issue1.setDepartment(issue.getDepartment());
		issue1.setLastUpdatedDate(issue.getLastUpdatedDate());
		issue1.setStatus(issue.getStatus());
//		issue1.setDetails(issue.getDetails());
		et.commit();
		em.close();
	}

	public void changeStatus(Issue issue, Status status) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		Issue accessedIssue = em.find(Issue.class, issue.getId());
		et.begin();
		accessedIssue.setStatus(status);
		et.commit();
		em.close();
	}

	public void reassignHelper(Issue issue, Department department, User admin) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		Issue accessedIssue = em.find(Issue.class, issue.getId());
		et.begin();
		accessedIssue.setDepartment(department);
		accessedIssue.setAdmin(admin);
		et.commit();
		em.close();
	}

}
