package com.einsicht.mvc;

import org.springframework.web.servlet.ModelAndView;

public class MessageModelAndView extends ModelAndView {
	
	public MessageModelAndView() {
		withTitle("");
		withMessage("");
		setViewName("pages/message");
	}

	public MessageModelAndView withTitle(String title) {
		addObject("title", title);
		return this;
	}

	public MessageModelAndView withMessage(String message) {
		addObject("message", message);
		return this;
	}
}
