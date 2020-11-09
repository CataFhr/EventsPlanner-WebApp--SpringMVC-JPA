package ro.telacad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ro.telacad.dao.GuestDao;
import ro.telacad.db.Guest;

@Service
public class GuestService {

	@Autowired
	private GuestDao guestDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public void addGuest(Guest guest) {
		int eventId = guest.getEvent().getId();
		Optional<Guest> optionalGuest = guestDao.findByEmail(guest.getEmail(), eventId);
		if (optionalGuest.isEmpty()) {
			guestDao.addGuest(guest);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public List<Guest> findByEventId(int eventId) {
		return guestDao.findByEventId(eventId);
	}
}
