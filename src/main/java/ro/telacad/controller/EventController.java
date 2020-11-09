package ro.telacad.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ro.telacad.db.Adress;
import ro.telacad.db.Event;
import ro.telacad.db.User;
import ro.telacad.service.EventService;
import ro.telacad.service.UserService;

@Controller
public class EventController {

	@Autowired
	private EventService eventService;

	@Autowired
	private UserService userService;

	@RequestMapping("/viewEv/{userId}")
	public String showViewEventPage(@PathVariable("userId") int userId, Model model) {
		List<Event> userEvents = eventService.findByUserId(userId);
		Collections.sort(userEvents);
		List<Event> allEvents = eventService.findAll();
		Collections.sort(allEvents);
		model.addAttribute("list1", userEvents);
		model.addAttribute("list2", allEvents);
		model.addAttribute("idUser", userId);
		return "viewevents";
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		return "login-page";
	}

	@RequestMapping("/addEv/{idUser}")
	public String showAddEventPage(@PathVariable("idUser") int userId, Model model) {
		model.addAttribute("idUser", userId);
		return "addevent";
	}

	@RequestMapping(value = "/process-addevent/{idUser}", method = RequestMethod.POST)
	public String addEvent(@PathVariable("idUser") int userId, @ModelAttribute("event") Event event,
			@ModelAttribute("adress") Adress adress, Model model) {
		User user = userService.findUserById(userId);
		try {
			if (event.getDate().isBefore(LocalDate.now())) {
				throw new RuntimeException();
			}
			event.setUser(user);
			event.setAdress(adress);
			eventService.addEvent(event);
			model.addAttribute("userId", userId);
			return "redirect:/viewEv/{userId}";
		} catch (IllegalArgumentException e) {
			model.addAttribute("message", "There is already an event on this date.");
		} catch (RuntimeException e) {
			model.addAttribute("message", "Incorrect date.");
		}
		return "addevent";
	}

	@RequestMapping(value = "/deleteevent/{idEvent}", method = RequestMethod.GET)
	public String delete(@PathVariable("idEvent") int eventId, Model model) {
		Event event = eventService.findEventById(eventId);
		int userId = event.getUser().getId();
		model.addAttribute("userId", userId);
		eventService.deleteEvent(event);
		return "redirect:/viewEv/{userId}";
	}

	@RequestMapping(value = "/editevent/{idEvent}")
	public String showEditEventPage(@PathVariable("idEvent") int eventId, Model model) {
		Event event = eventService.findEventById(eventId);
		model.addAttribute("ev", event);
		return "editevent";
	}

	@RequestMapping(value = "/process-editevent/{idEvent}/{idUser}", method = RequestMethod.POST)
	public String editEvent(@PathVariable("idEvent") int eventId,@PathVariable("idUser") int userId, @ModelAttribute("event") Event event,
			@ModelAttribute("adress") Adress adress, Model model) {
		try {
			if (event.getDate().isBefore(LocalDate.now())) {
				throw new RuntimeException();
			}
			event.setId(eventId);
			event.setAdress(adress);
			eventService.updateEvent(event);
			model.addAttribute("userId", userId);
			return "redirect:/viewEv/{userId}";
		} catch (IllegalArgumentException e) {
			model.addAttribute("message",
					"There is already an event on this date. Click \"back\" arrow and try again.");
		} catch (RuntimeException e) {
			model.addAttribute("message", "Incorrect date. Click \"back\" arrow and try again.");
		}
		return "editevent";
	}
}
