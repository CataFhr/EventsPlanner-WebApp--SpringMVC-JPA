package ro.telacad.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ro.telacad.db.User;

@Repository
public class UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	public User addUser(User user) {
		entityManager.persist(user);
		return user;
	}

	public Optional<User> findByUsername(String username) {
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username",
				User.class);
		query.setParameter("username", username);
		return query.getResultList().stream().findFirst();
	}

	public User findUserById(int userId) {
		return entityManager.find(User.class, userId);
	}
}
