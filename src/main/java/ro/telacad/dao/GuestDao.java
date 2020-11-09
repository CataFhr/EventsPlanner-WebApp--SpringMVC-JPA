package ro.telacad.dao;

import java.util.List;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ro.telacad.db.Guest;

@Repository
public class GuestDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void addGuest(Guest guest) {
		entityManager.persist(guest);
	}

	public List<Guest> findByEventId(int eventId) {
		TypedQuery<Guest> query = entityManager.createQuery("SELECT g FROM Guest g JOIN g.event e WHERE e.id = :id",
				Guest.class);
		query.setParameter("id", eventId);
		return query.getResultList();
	}

	public Optional<Guest> findByEmail(String email, int eventId) {
		TypedQuery<Guest> query = entityManager.createQuery(
				"SELECT g FROM Guest g JOIN g.event e WHERE  e.id = :id AND g.email = :email", Guest.class);
		query.setParameter("id", eventId);
		query.setParameter("email", email);
		return query.getResultList().stream().findFirst();
	}
}
