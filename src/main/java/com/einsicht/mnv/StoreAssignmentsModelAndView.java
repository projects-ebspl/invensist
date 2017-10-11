package com.einsicht.mnv;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.einsicht.models.StoreSelectionModel;
import com.einsicht.models.UserModel;

public class StoreAssignmentsModelAndView extends ModelAndView {
	
	public StoreAssignmentsModelAndView() {
		setViewName("pages/msa2");
	}

	public StoreAssignmentsModelAndView setStores(List<StoreSelectionModel> stores) {
		this.addObject("stores", stores);
		return this;
	}
	
	public StoreAssignmentsModelAndView setUser(UserModel user) {
		this.addObject("user", user);
		return this;
	}
}
