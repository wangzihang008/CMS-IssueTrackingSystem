package com.fdmgroup.DAO;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.fdmgroup.Entities.IssueDetail;

public class IssueDetailDAO {

	@Resource(name = "emfBean")
	private EntityManagerFactory emf;

	public EntityManagerFactory getEmf() {
		return emf;
	}
	
	public IssueDetailDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public IssueDetailDAO() {

	}
	
	public void addIssueDetail(IssueDetail issueDetail) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(issueDetail);
		et.commit();
		em.close();
	}

	public IssueDetail getIssueDetail(long id) {
		EntityManager em = emf.createEntityManager();
		IssueDetail returnedIssueDetail = em.find(IssueDetail.class, id);
		em.close();
		return returnedIssueDetail;
	}
}
