package com.einsicht.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
import com.einsicht.services.InventoryService;


@Controller
public class ConfigController {
	
	@Autowired
	private ConfigService service;
	
	@Autowired
	InventoryService inventoryService;
	
	/**
	 * create/update user with given details
	 * if id exist means we are going to update existing user
	 * @param user
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/user")
	public ModelAndView user(@Valid @ModelAttribute("user")UserModel user, BindingResult bindingResult ) {		

		ModelAndView mv = new ModelAndView("pages/user");
		boolean editUser = false;
		
		if(user.getId() != null){
			editUser = true;
		}
		UserModel userExists = service.getUserByEmail(user.getEmail());
		mv.addObject("editUser", editUser);
		if (userExists != null && user.getId() == null) {
			bindingResult
			.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		}		
		if (!(user.isAdmin() || user.isPlanner() || user.isUser())) {
			bindingResult.rejectValue("admin", "error.user");
			mv.addObject("roleErrorMessage", "*Please select atleast one Role");			
		}
		if (bindingResult.hasErrors()) {
			return mv;
		} else {
			service.saveUser(user);
			return users();
		}		
	}
	
	@GetMapping("/user")
	public ModelAndView user() {
		ModelAndView mv = new ModelAndView("pages/user");
		mv.addObject("editUser", false);
		mv.addObject("user", new UserModel());
		return mv;
	}
	
	
	@PostMapping("/edit-user")
	public ModelAndView editUser(@RequestParam("ids") String id, @ModelAttribute("user")UserModel userModel, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("pages/user");
		UserModel user = service.getUserById(Integer.parseInt(id));
		mv.addObject("user", user);
		mv.addObject("editUser", true);
		return mv;
	}
	
	@PostMapping("/delete-users")
	public ModelAndView deleteUsers(@RequestParam("ids") String ids) {
		service.deleteUsers(ids); 
		System.out.println(ids);
		return users();
	}
	
	@PostMapping("/reset-password")
	public ModelAndView resetPassword(@Valid @ModelAttribute("resetPassword")ResetPassword resetPassword, BindingResult bindingResult) {

		ModelAndView mv = new ModelAndView("pages/reset-password");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!resetPassword.getNewPassword().equals(resetPassword.getConfirmNewPassword())) {
			bindingResult
			.rejectValue("resetPassword", "error.resetPassword",
					"new password and confirm new password does not match");
		}
		if (bindingResult.hasErrors()) {
			return mv;
		} else {
			boolean success = service.resetPassword(auth.getName(), resetPassword.getNewPassword());
			if(success) {
				return new SuccessMessageModelAndView("The password is resetted successfully.");
			} else {
				return new ErrorMessageModelAndView("Error");
			}
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
		List<StoreModel> stores =  inventoryService.getStores();
		ModelAndView mv = new ModelAndView("pages/stores");
		mv.addObject("stores", stores);
		return mv;
	}

	@PostMapping("/delete-stores")
	public ModelAndView deleteStores(@RequestParam("ids") String ids) {
		inventoryService.deleteStores(ids); 
		System.out.println(ids);
		return stores();
	}

	@PostMapping("/edit-store")
	public ModelAndView editStore(@RequestParam("ids") String id) {		
		ModelAndView mv = new ModelAndView("pages/store");		
		StoreModel store = inventoryService.getStore(Integer.parseInt(id));
		mv.addObject("store", store);
		mv.addObject("types", StoreType.values());
		mv.addObject("editStore", true);
		return mv;
	}

	/**
	 * save new store
	 * @param store
	 * @return ModelAndView
	 */
	@PostMapping("/store")
	public ModelAndView store(@ModelAttribute("store")StoreModel store, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("pages/store");
		boolean success = inventoryService.addStore(store);
		boolean editStore = false;
		
		if(store.getId() != null){
			editStore = true;
		}
		mv.addObject("editStore", editStore);
		if (success) {
			return  stores();
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
