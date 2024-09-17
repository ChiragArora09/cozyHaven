package com.group_3.cozyHaven.dto;

public class BusAvailableSeatsDto {
	
	private int id;
	private String seatNumber;
	private String seatType;
	
	public BusAvailableSeatsDto(int id, String seatNumber, String seatType) {
		super();
		this.id = id;
		this.seatNumber = seatNumber;
		this.seatType = seatType;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	
}
