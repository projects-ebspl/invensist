package com.einsicht.entities;

public enum Role { ADMIN("admin",1),PLANNER("planner",2),USER("user",3);
	
	private String name;
	
	private String description;
	
	private int id;
	
	Role(String name, int id){
		this.name = name;
		this.id = id;
	}	

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
	 * Returns Role for the given name
	 * @param Name
	 * @return Role
	 */
	public static Role geRoleByName(String name) {
		for (Role role : values()) {
			if(role.name.equalsIgnoreCase(name)) return role;
		}
		return null;
	}
	
	/**
	 * Returns Role for the given id
	 * @param  id
	 * @return Role
	 */
	public static Role geRoleById(int id) {
		for (Role role : values()) {
			if(role.id == id) return role;
		}
		return null;
	}
}
