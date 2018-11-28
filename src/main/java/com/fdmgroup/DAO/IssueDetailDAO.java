package com.fdmgroup.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.fdmgroup.Entities.Issue;
import com.fdmgroup.Entities.IssueDetail;

public class IssueDetailDAO {

	@Resource(name = "emfBean")
	private EntityManagerFactory emf;

	public IssueDetailDAO(EntityManagerFactory emf) {
		// TODO Auto-generated constructor stub
		this.emf = emf;
	}
	
	public IssueDetailDAO() {
		
	}

	public EntityManagerFactory getEmf() {
		return emf;
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
	
	public List<IssueDetail> getIssueDetailsByIssue(Issue issue) {
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery(
				"SELECT d FROM IssueDetail d WHERE issue_id = '" + issue.getId() + "'", IssueDetail.class);
		List<IssueDetail> issueDetails = query.getResultList();
		return issueDetails;
	}

}
