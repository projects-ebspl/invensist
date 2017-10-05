package com.einsicht.models;

import java.util.List;
import java.util.stream.Collectors;

public class UserStoreModel {
	
	private UserModel user;
	
	private List<StoreModel> stores;

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public List<StoreModel> getStores() {
		return stores;
	}

	public void setStores(List<StoreModel> stores) {
		this.stores = stores;
	}
	
	public int getUserId() {
		return user.getId();
	}
	
	public String getUserName() {
		return user.getName();
	}
	
	public String getStoresCsv() {
		return stores != null ? stores.stream().map( store -> store.getName()).collect(Collectors.joining(",")) : "";
	}
	
}
