package com.einsicht.models;

import java.util.List;

public class DualSelectorModel<Item extends HasLabel> {
	
	private List<Item> availableItems;
	
	private List<Item> selectedItems;

	public List<Item> getAvailableItems() {
		return availableItems;
	}

	public void setAvailableItems(List<Item> availableItems) {
		this.availableItems = availableItems;
	}

	public List<Item> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<Item> selectedItems) {
		this.selectedItems = selectedItems;
	}
}
