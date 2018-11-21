package com.fdmgroup.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.fdmgroup.Entities.User;

public class UserDAO {

	Connection conn = null;
	private static final String UPDATE = "UPDATE USERS SET username=?, password=?, type=?, status=?, department=?, firstName=?, lastName=?, issues=? WHERE id=?";

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

	public void updateUser(User user) {
		try {
			PreparedStatement ps = conn.prepareStatement(UPDATE);

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getType().ordinal());
			ps.setInt(4, user.getStatus().ordinal());
			ps.setLong(5, user.getDepartment().getId());
			ps.setString(6, user.getFirstName());
			ps.setString(7, user.getLastName());
			// issue column needs further implementation
			ps.executeUpdate();
			ps.close();
			System.out.println("User with id " + user.getId() + " was updated in DataBase with following details: "
					+ user.toString());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
