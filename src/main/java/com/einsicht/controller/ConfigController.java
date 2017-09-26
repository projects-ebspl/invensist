package com.einsicht.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.einsicht.models.ResetPassword;
import com.einsicht.models.User;
import com.einsicht.mvc.ErrorMessageModelAndView;
import com.einsicht.mvc.SuccessMessageModelAndView;


@Controller
public class ConfigController {
	
	@PostMapping("/user")
	public ModelAndView user(@ModelAttribute("user")User user) {
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
		mv.addObject("user", new User());
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
	
	
}
