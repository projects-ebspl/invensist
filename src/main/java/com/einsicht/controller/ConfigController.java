package com.einsicht.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.einsicht.enums.StoreType;
import com.einsicht.models.ResetPassword;
import com.einsicht.models.StoreModel;
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
		boolean success = service.saveUser(user);

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
	
	
	@PostMapping("/edit-user")
	public ModelAndView editUser(@RequestParam("ids") String id) {
		ModelAndView mv = new ModelAndView("pages/user");
		UserModel user = new UserModel();
		user.setId(1);
		user.setFirstName("FN:" + 1);
		user.setLastName("LN" + 1);
		user.setEmail("email@einsicht.com");
		user.setAdmin(true);
		user.setPhone("123456789");
		mv.addObject("user", user);
		return mv;
	}
	
	@PostMapping("/delete-users")
	public ModelAndView deleteUsers(@RequestParam("ids") String ids) {
		service.deleteUsers(ids); 
		System.out.println(ids);
		return users();
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
		List<UserModel> users =  service.getUsers();
		ModelAndView mv = new ModelAndView("pages/users");
		mv.addObject("users", users);
		return mv;
	}
	
	@GetMapping("/stores")
	public ModelAndView stores() {
		ArrayList<StoreModel> stores =  new ArrayList<StoreModel>();
		for (int i = 1; i <= 5; i++) {
			StoreModel store = new StoreModel();
			store.setId(i);
			switch(i) {
				case 1: 
					store.setName("Main");
					store.setType(StoreType.regular);
					break;
				case 2: 
					store.setName("Line");
					store.setType(StoreType.assembly);
					break;
				case 3:
					store.setName("Wastage-1");
					store.setType(StoreType.wastage);
					break;
				case 4:
					store.setName("Shortage-1");
					store.setType(StoreType.shortage);
					break;
				case 5:
					store.setName("Reject-1");
					store.setType(StoreType.rejection);
					break;
			}
			stores.add(store);
		}
		ModelAndView mv = new ModelAndView("pages/stores");
		mv.addObject("stores", stores);
		return mv;
	}

	@PostMapping("/delete-stores")
	public ModelAndView deleteStores(@RequestParam("ids") String ids) {
		// TODO Delete 
		System.out.println(ids);
		return stores();
	}

	@PostMapping("/edit-store")
	public ModelAndView editStore(@RequestParam("ids") String id) {
		ModelAndView mv = new ModelAndView("pages/store");
		StoreModel store = new StoreModel();
		store.setId(1);
		store.setName("WASTAGE-2");
		store.setType(StoreType.wastage);
		mv.addObject("store", store);
		return mv;
	}

	@PostMapping("/store")
	public ModelAndView store(@ModelAttribute("store")StoreModel store) {
		// TODO Save Store
		boolean success = true;
		if(success) {
			return new SuccessMessageModelAndView("The store has been added successfully.");
		} else {
			return new ErrorMessageModelAndView("Error");
		}
	}
	
	@GetMapping("/store")
	public ModelAndView store() {
		ModelAndView mv = new ModelAndView("pages/store");
		mv.addObject("store", new StoreModel());
		mv.addObject("types", StoreType.values());
		return mv;
	}
}
