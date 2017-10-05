package com.einsicht.models;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UserModel {
	
	private int id;
	
	@NotEmpty(message ="*FirstName is mandatory")
	private String firstName;
	
	private String lastName;
	
	@NotEmpty(message = "*email is mandatory")
	@Email(message = "*Please provide a valid email")
	private String email;
	
	@NotEmpty(message = "*phone is mandatory")
	@Size(min=6,max=10)
	private String phone;
	
	private String address;
	
	@NotEmpty(message = "*password is mandatory")
	@Size(min=6,max=10)
	private String password;
	
	private boolean admin;
	
	private boolean planner;
	
	private boolean user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the admin
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
	 * @return the planner
	 */
	public boolean isPlanner() {
		return planner;
	}

	/**
	 * @param planner the planner to set
	 */
	public void setPlanner(boolean planner) {
		this.planner = planner;
	}

	/**
	 * @return the user
	 */
	public boolean isUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(boolean user) {
		this.user = user;
	}
	
	public String getName() {
		return getFirstName() + " " + getLastName();
	}
}
