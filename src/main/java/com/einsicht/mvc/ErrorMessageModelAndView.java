package com.einsicht.mvc;

public class ErrorMessageModelAndView extends MessageModelAndView {
	
	public ErrorMessageModelAndView() {
		this("");
	}
	
	public ErrorMessageModelAndView(String message) {
		withTitle("Error");
	}
	
}
