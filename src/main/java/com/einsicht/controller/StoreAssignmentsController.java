package com.einsicht.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.einsicht.mnv.StoreAssignmentsModelAndView;
import com.einsicht.models.StoreModel;
import com.einsicht.models.StoreSelectionModel;
import com.einsicht.services.ConfigService;
import com.einsicht.services.InventoryService;

@Controller
public class StoreAssignmentsController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private ConfigService configService;
	
	@PostMapping("/assign-stores")
	public ModelAndView assignStores(@RequestParam("userId") int userId, @RequestParam("storeIds") String storeIds) {
		List<Integer> storeIdsAsInts = Arrays.asList(storeIds.split(","))
		.stream()
		.map(storeId -> Integer.parseInt(storeId))
		.collect(Collectors.toList());
		inventoryService.assignStore(userId, storeIdsAsInts);
		return storeAssignment();
	}
	
	@GetMapping("/store-assignment")
	public ModelAndView storeAssignment() {
		ModelAndView mv = new ModelAndView("pages/store-assignment");
		mv.addObject("models", inventoryService.getUserStoreAssignments(configService.getUsers()));
		return mv;
	}
	
	
	@GetMapping(value = "/msa1")
	public ModelAndView msa1() {
		ModelAndView mv = new ModelAndView("pages/msa1");
		mv.addObject("users", configService.getUsers());
		return mv;
	}
	

	
	@GetMapping(value = "/msa2")
	public StoreAssignmentsModelAndView manageStoreAssignments(@RequestParam(value = "ids") Integer userId) {
		
		List<StoreModel> stores = inventoryService.getStores();
		List<StoreModel> selectedStores = inventoryService.getStoresForUser(userId);
		
		List<StoreSelectionModel> selections = stores.stream().map(store -> {
			return new StoreSelectionModel(store, containsStore(store, selectedStores));
		}).collect(Collectors.toList());
		
		return new StoreAssignmentsModelAndView()
		.setUser(configService.getUserById(userId))
		.setStores(selections);
	}
	
	private boolean containsStore(StoreModel store, List<StoreModel> stores) {
		for (StoreModel storeModel : stores) {
			if(store.getId().equals(storeModel.getId())) {
				return true;
			}
		}
		return false;
	}
}
