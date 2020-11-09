package ro.telacad.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ro.telacad.dao.EventDao;
import ro.telacad.db.Event;

@Service
public class EventService {

	@Autowired
	private EventDao eventDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public void addEvent(Event event) {
		Optional<Event> optionalEvent = eventDao.findByDate(event.getDate());
		if (optionalEvent.isEmpty()) {
			eventDao.addEvent(event);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public List<Event> findAll() {
		return eventDao.findAll();
	}

	public List<Event> findByUserId(int userId) {
		return eventDao.findByUserId(userId);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteEvent(Event event) {
		eventDao.deleteEvent(event);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateEvent(Event newEvent) {
		Optional<Event> optionalEvent = eventDao.findByDate(newEvent.getDate());
		if (optionalEvent.isEmpty()) {
			eventDao.updateEvent(newEvent);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public Event findEventById(int eventId) {
		return eventDao.findEventById(eventId);
	}
}
