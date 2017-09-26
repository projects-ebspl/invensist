package com.einsicht.controller;




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
//	@GetMapping("/reset-password")
//	public ModelAndView resetPassword(Model model) {
//		model.addAttribute("resetPassword", new ResetPassword());
//		return "pages/user";
//	}
//	
//	@GetMapping("/reset-password")
//	public String resetPassword(Model model) {
//		model.addAttribute("resetPassword", new ResetPassword());
//		return "pages/user";
//	}
	
	
}
