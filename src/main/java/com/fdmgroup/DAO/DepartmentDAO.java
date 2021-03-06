package com.fdmgroup.DAO;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

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
	
	/**
	 * 
	 * @param name
	 * @return department by department name
	 */
	public Department getDepartment(String name) {
		EntityManager em = emf.createEntityManager();
		Department returnedDepartment = null;
		TypedQuery<Department> query = 
				(TypedQuery<Department>) em.createQuery("select d from Department d where d.name=:name");
		query.setParameter("name", name);
		ArrayList<Department> list = (ArrayList<Department>) query.getResultList();
		if(!list.isEmpty()) {
			returnedDepartment = list.get(0);
		}
		em.close();
		return returnedDepartment;
	}
	
	/**
	 * 
	 * @return a list of all departments in database
	 */
	public ArrayList<Department> getAllDepartment(){
		EntityManager em = emf.createEntityManager();
		TypedQuery<Department> query = 
				(TypedQuery<Department>) em.createQuery("select d from Department d");
		ArrayList<Department> list = (ArrayList<Department>) query.getResultList();
		em.close();
		return list;
	}
}
