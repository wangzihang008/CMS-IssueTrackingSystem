package com.fdmgroup.DAO;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.fdmgroup.Entities.Issue;

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
		return result;
	}
	
	/**
	 * 
	 * @param adminId
	 * @return a list of issue, given admin id
	 */
	public ArrayList<Issue> getIssuesByAdminId(long adminId){
		EntityManager em = emf.createEntityManager();
		
		String str = "select i from Issue i WHERE i.admin=:admin";
		TypedQuery<Issue> query = (TypedQuery<Issue>) em.createQuery(str);
		query.setParameter("admin", adminId);
		ArrayList<Issue> result = (ArrayList<Issue>) query.getResultList();
		em.close();
		return result;
	}
}
