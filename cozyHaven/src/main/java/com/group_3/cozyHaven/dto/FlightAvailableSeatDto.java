package com.group_3.cozyHaven.dto;

public class FlightAvailableSeatDto {
	private int id;
	private String seatNumber;
	private String seatType;
	private String classType;

	public FlightAvailableSeatDto(int id, String seatNumber, String seatType, String classType) {
		super();
		this.id = id;
		this.seatNumber = seatNumber;
		this.seatType = seatType;
		this.classType = classType;
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

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

}
