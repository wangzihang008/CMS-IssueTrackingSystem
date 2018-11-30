package com.fdmgroup.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.fdmgroup.Entities.Department;
import com.fdmgroup.Entities.User;

public class UserDAO {

	@Resource(name = "emfBean")
	private EntityManagerFactory emf;

	public UserDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public UserDAO() {
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void addUser(User user) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(user);
		et.commit();
		em.close();
	}

	public User getUser(long id) {
		EntityManager em = emf.createEntityManager();
		User returnedUser = em.find(User.class, id);
		em.close();
		return returnedUser;
	}

	public User get(String username) {
		EntityManager em = emf.createEntityManager();

		TypedQuery<User> query = em.createQuery("SELECT u FROM User AS u WHERE u.username=:username", User.class);
		query.setParameter("username", username);
		List<User> user = query.getResultList();
		if (user.size() > 0) {
			em.close();
			return user.get(0);
		} else {
			em.close();
			return null;
		}
	}

	/**
	 * 
	 * @param depId
	 * @return return all admins who work in the department with department id
	 */
	public ArrayList<User> getUserByDep(Department department) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<User> query = (TypedQuery<User>) em
				.createQuery("select u from User u where u.department=:department");
		query.setParameter("department", department);
		ArrayList<User> result = (ArrayList<User>) query.getResultList();
		em.close();
		return result;
	}
}
