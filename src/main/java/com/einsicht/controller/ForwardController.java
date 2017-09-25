package com.einsicht.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ForwardController {
	
	@RequestMapping(value="/page/{page}", method = RequestMethod.GET)
	public ModelAndView forward(@PathVariable("page") String page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/" + page);
		return modelAndView;
	}
	
}
