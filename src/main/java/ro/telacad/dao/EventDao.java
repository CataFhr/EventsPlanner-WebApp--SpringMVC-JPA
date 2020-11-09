package ro.telacad.dao;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ro.telacad.db.Event;

@Repository
public class EventDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void addEvent(Event event) {
		entityManager.persist(event);
	}

	public List<Event> findAll() {
		TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e", Event.class);
		return query.getResultList();
	}

	public List<Event> findByUserId(int userId) {
		TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e JOIN e.user u WHERE u.id = :id",
				Event.class);
		query.setParameter("id", userId);
		return query.getResultList();
	}

	public Optional<Event> findByDate(LocalDate date) {
		TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e WHERE e.date = :date", Event.class);
		query.setParameter("date", date);
		return query.getResultList().stream().findFirst();
	}

	public void deleteEvent(Event event) {
		event = entityManager.merge(event);
		entityManager.remove(event);
	}

	public void updateEvent(Event newEvent) {
		Event event = entityManager.find(Event.class, newEvent.getId());
		event.setName(newEvent.getName());
		event.setDate(newEvent.getDate());
		event.setAdress(newEvent.getAdress());
	}

	public Event findEventById(int eventId) {
		return entityManager.find(Event.class, eventId);
	}

}
