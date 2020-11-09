package ro.telacad.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ro.telacad.db.User;
import ro.telacad.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String showLoginPage() {
		return "login-page";
	}

	@RequestMapping(value = "/process-loginpage", params = "register", method = RequestMethod.POST)
	public String registation(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			List<ObjectError> errorList = bindingResult.getAllErrors();
			model.addAttribute("message", errorList.get(0).getDefaultMessage());
			return "login-page";
		}
		try {
			userService.registration(user);
			model.addAttribute("message", "The user has been registered.");
		} catch (IllegalArgumentException e) {
			model.addAttribute("message", "Username already exists.");
		}
		return "login-page";
	}

	@RequestMapping(value = "/process-loginpage", params = "login", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			List<ObjectError> errorList = bindingResult.getAllErrors();
			model.addAttribute("message", errorList.get(0).getDefaultMessage());
			return "login-page";
		}
		try {
			int userId = userService.login(user);
			model.addAttribute("userId", userId);
			return "redirect:/viewEv/{userId}";
		} catch (IllegalArgumentException e) {
			model.addAttribute("message", "Username or password is incorrect.");
		}
		return "login-page";
	}

}
