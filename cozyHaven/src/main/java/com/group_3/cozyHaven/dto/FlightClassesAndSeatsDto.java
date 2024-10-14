package com.group_3.cozyHaven.dto;

public class FlightClassesAndSeatsDto {
	private int classId;
	private int seatId;
	private String classType;
	private String seatType;
	private String seatNumber;
	
	public FlightClassesAndSeatsDto(int classId, int seatId, String classType, String seatType, String seatNumber) {
		super();
		this.classId = classId;
		this.seatId = seatId;
		this.classType = classType;
		this.seatType = seatType;
		this.seatNumber = seatNumber;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

}
