package com.einsicht.entities;

import java.util.Date;

public class Order {
	private int id;
	private int clientId;
	private Date generatedDateTime;
	private Date dueDateTime;
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
	 * @return the clientId
	 */
	public int getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the generatedDateTime
	 */
	public Date getGeneratedDateTime() {
		return generatedDateTime;
	}
	/**
	 * @param generatedDateTime the generatedDateTime to set
	 */
	public void setGeneratedDateTime(Date generatedDateTime) {
		this.generatedDateTime = generatedDateTime;
	}
	/**
	 * @return the dueDateTime
	 */
	public Date getDueDateTime() {
		return dueDateTime;
	}
	/**
	 * @param dueDateTime the dueDateTime to set
	 */
	public void setDueDateTime(Date dueDateTime) {
		this.dueDateTime = dueDateTime;
	}
}
