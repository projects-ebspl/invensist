package com.einsicht.mvc;

public class SuccessMessageModelAndView extends MessageModelAndView {
	
	public SuccessMessageModelAndView() {
		this("");
	}
	
	public SuccessMessageModelAndView(String message) {
		withTitle("Success");
		withMessage(message);
	}
	
}
