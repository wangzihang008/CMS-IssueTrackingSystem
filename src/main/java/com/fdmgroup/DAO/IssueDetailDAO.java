package com.fdmgroup.DAO;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.fdmgroup.Entities.IssueDetail;
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

	public ArrayList<IssueDetail> getIssueDetailsByIssueId(long issueId) {
		EntityManager em = emf.createEntityManager();
		String str = "select id from IssueDetail id WHERE id.issue=:issue";
		TypedQuery<IssueDetail> query = (TypedQuery<IssueDetail>) em.createQuery(str);
		query.setParameter("issue", issueId);
		ArrayList<IssueDetail> result = (ArrayList<IssueDetail>) query.getResultList();
		em.close();
		return result;
	}
}
