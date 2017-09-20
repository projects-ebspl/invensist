package com.einsicht.entities;

import com.einsicht.enums.ItemType;

public class Item {
	
	private int id;
	
	private String code;
	
	private String description;
	
	private double itemcost;
	
	private double assemblycost;
	
	private ItemType type;
	
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the itemcost
	 */
	public double getItemcost() {
		return itemcost;
	}

	/**
	 * @param itemcost the itemcost to set
	 */
	public void setItemcost(double itemcost) {
		this.itemcost = itemcost;
	}

	/**
	 * @return the assemblycost
	 */
	public double getAssemblycost() {
		return assemblycost;
	}

	/**
	 * @param assemblycost the assemblycost to set
	 */
	public void setAssemblycost(double assemblycost) {
		this.assemblycost = assemblycost;
	}

	/**
	 * @return the type
	 */
	public ItemType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ItemType type) {
		this.type = type;
	}
}
