package com.fdmgroup.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.fdmgroup.Entities.User;

public class UserDAO {

	Connection conn = null;
	private static final String UPDATE = "UPDATE USERS SET username=?, password=?, type=?, status=?, department=?, firstName=?, lastName=?, issues=? WHERE id=?";

	@Resource(name = "emfBean")
	private EntityManagerFactory emf;

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

	public User getUser(Long id) {
		EntityManager em = emf.createEntityManager();
		User returnedUser = em.find(User.class, id);
		em.close();
		return returnedUser;
	}

	public void updateUser(User user) {
		try {
			PreparedStatement ps = conn.prepareStatement(UPDATE);
			// need further implementation
			// ps.setString(1, user.getFirstName());
			// ps.setString(2, user.getLastName());
			// ps.setString(3, user.getType().name());
			// ps.setString(6, user.getPassword());
			ps.executeUpdate();
			ps.close();
			System.out.println("User with id " + user.getId() + " was updated in DataBase with following details: "
					+ user.toString());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * @param depId
	 * @return return all admins who work in the department with department id
	 */
	public ArrayList<User> getUserByDep(long depId){
		EntityManager em = emf.createEntityManager();
		TypedQuery<User> query = (TypedQuery<User>) em.createQuery("select u from User u where u.department=:department");
		query.setParameter("department", depId);
		ArrayList<User> result = (ArrayList<User>) query.getResultList();
		em.close();
		return result;
	}
}
