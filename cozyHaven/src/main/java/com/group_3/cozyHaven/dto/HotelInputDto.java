package com.group_3.cozyHaven.dto;

import java.time.LocalDate;

public class HotelInputDto {
	
	private String location;
	
	private LocalDate checkInDate;
	
	private LocalDate checkOutDate;
	
	private int numberGuests;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public int getNumberGuests() {
		return numberGuests;
	}

	public void setNumberGuests(int numberGuests) {
		this.numberGuests = numberGuests;
	}

	public HotelInputDto(String location, LocalDate checkInDate, LocalDate checkOutDate, int numberGuests) {
		super();
		this.location = location;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.numberGuests = numberGuests;
	}
	
	

}
