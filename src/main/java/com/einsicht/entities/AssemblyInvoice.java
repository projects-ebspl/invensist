package com.einsicht.entities;

import java.util.Date;

public class AssemblyInvoice {
	private int id;
	private int order;
	private int vendour;
	private Date due;
	private double invoicecost;
	private double totalcost;
	private String grn;
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
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}
	/**
	 * @return the vendour
	 */
	public int getVendour() {
		return vendour;
	}
	/**
	 * @param vendour the vendour to set
	 */
	public void setVendour(int vendour) {
		this.vendour = vendour;
	}
	/**
	 * @return the due
	 */
	public Date getDue() {
		return due;
	}
	/**
	 * @param due the due to set
	 */
	public void setDue(Date due) {
		this.due = due;
	}
	/**
	 * @return the invoicecost
	 */
	public double getInvoicecost() {
		return invoicecost;
	}
	/**
	 * @param invoicecost the invoicecost to set
	 */
	public void setInvoicecost(double invoicecost) {
		this.invoicecost = invoicecost;
	}
	/**
	 * @return the totalcost
	 */
	public double getTotalcost() {
		return totalcost;
	}
	/**
	 * @param totalcost the totalcost to set
	 */
	public void setTotalcost(double totalcost) {
		this.totalcost = totalcost;
	}
	/**
	 * @return the grn
	 */
	public String getGrn() {
		return grn;
	}
	/**
	 * @param grn the grn to set
	 */
	public void setGrn(String grn) {
		this.grn = grn;
	}
}
