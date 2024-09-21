package com.group_3.cozyHaven.dto;

public class BusPaymentDto {
    private double amount;
    private String seat;
    private double totalAmount;
    
    
	public BusPaymentDto(double amount, String seat, double totalAmount) {
		super();
		this.amount = amount;
		this.seat = seat;
		this.totalAmount = totalAmount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
