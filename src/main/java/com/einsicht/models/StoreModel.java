package com.einsicht.models;

import com.einsicht.enums.StoreType;

public class StoreModel implements HasId, HasLabel {
	private int id;
	
	private String name;
	
	private StoreType type = StoreType.assembly;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StoreType getType() {
		return type;
	}

	public void setType(StoreType type) {
		this.type = type;
	}
	
	public String getStoreType() {
		return type.getType();
	}

	public void setStoreType(String storeType) {
		this.type = StoreType.valueOf(storeType)
				;
	}

	@Override
	public String getLabel() {
		return name;
	}

}
