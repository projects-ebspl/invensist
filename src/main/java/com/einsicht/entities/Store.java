package com.einsicht.entities;

import com.einsicht.enums.StoreType;

public class Store {
	private int id;
	
	private String name;
	
	private StoreType type;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public StoreType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(StoreType type) {
		this.type = type;
	}
}
