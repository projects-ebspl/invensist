package com.einsicht.enums;

public enum StoreType {
	regular("Regular"),
	rejection("Rejection"),
	assembly("Assembly"),
	wastage("Wastage"),
	shortage("Shortage");
	
	private final String type;
	
	private StoreType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
