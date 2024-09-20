package com.group_3.cozyHaven.model;

public class Payment {
    private double amount;
    private String seat;
    private String fclass;
    private double totalAmount;
 

	public Payment(double amount, String seat, String fclass, double totalAmount) {
		super();
		this.amount = amount;
		this.seat = seat;
		this.fclass = fclass;
		this.totalAmount = totalAmount;
	}
	
	public Payment(double amount, String seat, double totalAmount) {
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

	public String getFclass() {
		return fclass;
	}

	public void setFclass(String fclass) {
		this.fclass = fclass;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
    
}
