package com.group_3.cozyHaven.dto;

public class AddFlightLoyaltyPointsDto {
	private int bookingId;
	private int loyaltyPointsUsed;
	private int loyaltyPointsEarned;
	private String dateOfTransaction;
	
	public AddFlightLoyaltyPointsDto(int bookingId, int loyaltyPointsUsed, int loyaltyPointsEarned,
			String dateOfTransaction) {
		super();
		this.bookingId = bookingId;
		this.loyaltyPointsUsed = loyaltyPointsUsed;
		this.loyaltyPointsEarned = loyaltyPointsEarned;
		this.dateOfTransaction = dateOfTransaction;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getLoyaltyPointsUsed() {
		return loyaltyPointsUsed;
	}

	public void setLoyaltyPointsUsed(int loyaltyPointsUsed) {
		this.loyaltyPointsUsed = loyaltyPointsUsed;
	}

	public int getLoyaltyPointsEarned() {
		return loyaltyPointsEarned;
	}

	public void setLoyaltyPointsEarned(int loyaltyPointsEarned) {
		this.loyaltyPointsEarned = loyaltyPointsEarned;
	}

	public String getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(String dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
}
