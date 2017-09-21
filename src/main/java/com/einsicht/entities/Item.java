package com.einsicht.entities;

import com.einsicht.enums.ItemType;

public class Item {
	
	private int id;
	
	private String code;
	
	private String description;
	
	private Double itemcost;
	
	private Double assemblycost;
	
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
	public Item setId(int id) {
		this.id = id;
		return this;
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
	public Item setCode(String code) {
		this.code = code;
		return this;
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
	public Item setDescription(String description) {
		this.description = description;
		return this;
	}

	/**
	 * @return the itemcost
	 */
	public Double getItemcost() {
		return itemcost;
	}

	/**
	 * @param itemcost the itemcost to set
	 */
	public Item setItemcost(Double itemcost) {
		this.itemcost = itemcost;
		return this;
	}

	/**
	 * @return the assemblycost
	 */
	public Double getAssemblycost() {
		return assemblycost;
	}

	/**
	 * @param assemblycost the assemblycost to set
	 */
	public Item setAssemblycost(Double assemblycost) {
		this.assemblycost = assemblycost;
		return this;
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
	public Item setType(ItemType type) {
		this.type = type;
		return this;
	}
}
