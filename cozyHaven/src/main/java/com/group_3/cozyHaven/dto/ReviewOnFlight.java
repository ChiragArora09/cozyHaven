package com.group_3.cozyHaven.dto;

public class ReviewOnFlight {
	private int bookingId;
	private String description;
	private int star;
	private String date;
	private String source;
	private String destination;
	private String customerName;
	private String email;
	
	public ReviewOnFlight(int bookingId, String description, int star, String date, String source, String destination, String customerName, String email) {
		super();
		this.bookingId = bookingId;
		this.description = description;
		this.star = star;
		this.date = date;
		this.source = source;
		this.destination = destination;
		this.customerName = customerName;
		this.email = email;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
