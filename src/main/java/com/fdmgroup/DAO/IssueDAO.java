package com.fdmgroup.DAO;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.fdmgroup.Entities.Issue;

public class IssueDAO {

	@Resource(name = "emfBean")
	private EntityManagerFactory emf;

	public EntityManagerFactory getEmf() {
		return emf;
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
	
}
