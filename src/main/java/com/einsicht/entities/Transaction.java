package com.einsicht.entities;

import java.util.Date;

public class Transaction {
	private Date datetime;
	private int fromstore;
	private int tostore;
	private int item;
	private int quantity;
	/**
	 * @return the datetime
	 */
	public Date getDatetime() {
		return datetime;
	}
	/**
	 * @param datetime the datetime to set
	 */
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	/**
	 * @return the fromstore
	 */
	public int getFromstore() {
		return fromstore;
	}
	/**
	 * @param fromstore the fromstore to set
	 */
	public void setFromstore(int fromstore) {
		this.fromstore = fromstore;
	}
	/**
	 * @return the tostore
	 */
	public int getTostore() {
		return tostore;
	}
	/**
	 * @param tostore the tostore to set
	 */
	public void setTostore(int tostore) {
		this.tostore = tostore;
	}
	/**
	 * @return the item
	 */
	public int getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(int item) {
		this.item = item;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
