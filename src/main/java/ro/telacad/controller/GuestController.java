package ro.telacad.controller;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ro.telacad.db.Event;
import ro.telacad.db.Guest;
import ro.telacad.service.EventService;
import ro.telacad.service.GuestService;

@Controller
public class GuestController {

	@Autowired
	private GuestService guestService;

	@Autowired
	private EventService eventService;

	@RequestMapping(value = "/viewGuest/{idEvent}")
	public String showViewGuestPage(@PathVariable("idEvent") int eventId, Model model) {
		List<Guest> eventGuests = guestService.findByEventId(eventId);
		Collections.sort(eventGuests);
		model.addAttribute("list", eventGuests);
		model.addAttribute("idEv", eventId);
		return "viewguests";
	}

	@RequestMapping(value = "backAtEvents/{idEvent}", method = RequestMethod.GET)
	public String backAtEventsPage(@PathVariable("idEvent") int eventId, Model model) {
		Event event = eventService.findEventById(eventId);
		model.addAttribute("userId", event.getUser().getId());
		return "redirect:/viewEv/{userId}";
	}

	@RequestMapping("addGuest/{idEvent}")
	public String showAddGuestPage(@PathVariable("idEvent") int eventId, Model model) {
		model.addAttribute("idEv", eventId);
		return "addguest";
	}

	@RequestMapping(value = "process-addguest/{idEvent}", method = RequestMethod.POST)
	public String addGuest(@PathVariable("idEvent") int eventId, @ModelAttribute("guest") Guest guest,
			@RequestParam("phoneNo") Set<String> phoneNumbers, Model model) {
		Event event = eventService.findEventById(eventId);
		try {
			guest.setEvent(event);
			guest.setPhoneNumbers(phoneNumbers);
			guestService.addGuest(guest);
			model.addAttribute("eventId", eventId);
			return "redirect:/viewGuest/{eventId}";
		} catch (IllegalArgumentException e) {
			model.addAttribute("message",
					"The guest is already on the event list. Click \"back\" arrow and try again.");
		}
		return "addguest";
	}
}
