package com.einsicht.models;

import java.util.List;
import java.util.stream.Collectors;

public class UserStoreModel {
	
	private UserModel user;
	
	private List<StoreModel> stores;

	public UserModel getUser() {
		return user;
	}

	public UserStoreModel withUser(UserModel user) {
		this.user = user;
		return this;
	}

	public List<StoreModel> getStores() {
		return stores;
	}

	public UserStoreModel withStores(List<StoreModel> stores) {
		this.stores = stores;
		return this;
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
