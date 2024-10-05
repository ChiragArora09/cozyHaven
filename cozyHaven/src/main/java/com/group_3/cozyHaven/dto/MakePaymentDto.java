package com.group_3.cozyHaven.dto;

import java.time.LocalDate;

public class MakePaymentDto {
	private int discount;
	private int amount;
	private int loyaltyPoints;
	private LocalDate dateOfJourney;

	public MakePaymentDto(int discount, int amount, int loyaltyPoints, LocalDate dateOfJourney) {
		super();
		this.discount = discount;
		this.amount = amount;
		this.loyaltyPoints = loyaltyPoints;
		this.dateOfJourney = dateOfJourney;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	public LocalDate getDateOfJourney() {
		return dateOfJourney;
	}

	public void setDateOfJourney(LocalDate dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}
	
}
