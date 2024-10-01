package com.group_3.cozyHaven.dto;

import java.time.LocalDate;

public class HotelInputDto {
	
	private String location;

	private int numGuests;
	
	private int numRooms;
	
	private LocalDate checkInDate;
	
	private LocalDate checkOutDate;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public int getNumGuests() {
		return numGuests;
	}

	public void setNumGuests(int numGuests) {
		this.numGuests = numGuests;
	}

	
	public int getNumRooms() {
		return numRooms;
	}

	public void setNumRooms(int numRooms) {
		this.numRooms = numRooms;
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

	public HotelInputDto(String location, int numGuests, int numRooms, LocalDate checkInDate, LocalDate checkOutDate) {
		super();
		this.location = location;
		this.numGuests = numGuests;
		this.numRooms = numRooms;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}
	
	


	

	
	
	

}
