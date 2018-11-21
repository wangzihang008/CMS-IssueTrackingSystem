package com.fdmgroup.DAO;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.fdmgroup.Entities.Department;

public class DepartmentDAO {

	@Resource(name = "emfBean")
	private EntityManagerFactory emf;

	public DepartmentDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public DepartmentDAO() {

	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void addDepartment(Department d) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(d);
		et.commit();
		em.close();
	}

	public Department getDepartment(long id) {
		EntityManager em = emf.createEntityManager();
		Department returnedDepartment = em.find(Department.class, id);
		em.close();
		return returnedDepartment;
	}
}
