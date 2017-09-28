package com.einsicht.controller;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.einsicht.models.ResetPassword;
import com.einsicht.models.UserModel;
import com.einsicht.mvc.ErrorMessageModelAndView;
import com.einsicht.mvc.SuccessMessageModelAndView;
import com.einsicht.services.ConfigService;


@Controller
public class ConfigController {
	
	@Autowired
	private ConfigService service;
	
	@PostMapping("/user")
	public ModelAndView user(@ModelAttribute("user")UserModel user) {
		// TODO Save User
		boolean success = true;
		if(success) {
			return new SuccessMessageModelAndView("The user has been added successfully.");
		} else {
			return new ErrorMessageModelAndView("Error");
		}
	}
	
	@GetMapping("/user")
	public ModelAndView user() {
		ModelAndView mv = new ModelAndView("pages/user");
		mv.addObject("user", new UserModel());
		return mv;
	}
	
	@PostMapping("/reset-password")
	public ModelAndView resetPassword(@ModelAttribute("resetPassword")ResetPassword resetPassword) {
		// TODO Reset Password
		boolean success = true;
		if(success) {
			return new SuccessMessageModelAndView("The password is resetted successfully.");
		} else {
			return new ErrorMessageModelAndView("Error");
		}
	}
	
	@GetMapping("/reset-password")
	public ModelAndView resetPassword() {
		ModelAndView mv = new ModelAndView("pages/reset-password");
		mv.addObject("resetPassword", new ResetPassword());
		return mv;
	}
	
	@GetMapping("/users")
	public ModelAndView users() {
		ArrayList<UserModel> users =  new ArrayList<UserModel>();
		for (int i = 1; i <= 5; i++) {
			UserModel user = new UserModel();
			user.setFirstName("FN:" + i);
			user.setLastName("LN" + i);
			user.setEmail("email@einsicht.com");
			user.setAdmin(true);
			user.setPhone(i + "123456789");
			users.add(user);
		}
		ModelAndView mv = new ModelAndView("pages/users");
		mv.addObject("users", users);
		return mv;
	}
	
}
