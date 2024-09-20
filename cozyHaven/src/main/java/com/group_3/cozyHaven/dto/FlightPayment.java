package com.group_3.cozyHaven.dto;

public class FlightPayment {
	
	private double amount;
	private String flightSeatType;
	private String flightClassType;
	private double totalAmount;
	
	public FlightPayment(double amount, String flightSeatType, String flightClassType, double totalAmount) {
		super();
		this.amount = amount;
		this.flightSeatType = flightSeatType;
		this.flightClassType = flightClassType;
		this.totalAmount = totalAmount;
	}

	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getFlightSeatType() {
		return flightSeatType;
	}

	public void setFlightSeatType(String flightSeatType) {
		this.flightSeatType = flightSeatType;
	}

	public String getFlightClassType() {
		return flightClassType;
	}

	public void setFlightClassType(String flightClassType) {
		this.flightClassType = flightClassType;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
