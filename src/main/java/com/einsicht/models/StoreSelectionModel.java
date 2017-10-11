package com.einsicht.models;

public class StoreSelectionModel {

	private StoreModel store;
	
	private boolean selected;

	public StoreSelectionModel(StoreModel store, boolean selected) {
		this.store = store;
		this.selected = selected;
	}

	public StoreModel getStore() {
		return store;
	}

	public boolean isSelected() {
		return selected;
	}


	 
	
}
