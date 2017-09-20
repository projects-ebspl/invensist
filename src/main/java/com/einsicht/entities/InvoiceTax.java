package com.einsicht.entities;

public class InvoiceTax {
	private int id;
	
	private String name;
	
	private double percentage;

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
	 * @return the percentage
	 */
	public double getPercentage() {
		return percentage;
	}

	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	
}
