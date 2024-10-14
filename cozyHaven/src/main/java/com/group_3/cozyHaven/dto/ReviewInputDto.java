package com.group_3.cozyHaven.dto;

public class ReviewInputDto {
	private int bookingId;
	private int stars;
	private String description;
	
	public ReviewInputDto(int bookingId, int stars, String description) {
		super();
		this.bookingId = bookingId;
		this.stars = stars;
		this.description = description;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
