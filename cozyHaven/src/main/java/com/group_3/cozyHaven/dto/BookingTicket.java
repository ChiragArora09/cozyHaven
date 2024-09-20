package com.group_3.cozyHaven.dto;

// REUSING THIS SAME MODEL FOR BUS TICKET AS WELL
public class BookingTicket {
	private String bdate;
	private String source;
	private String destination;
	private String status;
	private String seatNumber;
	private String type; // flight type or bus type
	private String flightName; // busName in case of bus-ticket
	private String travellerName;
	private int age;
	private String vehicleNumber;
	private String seatType;


	public BookingTicket(String bdate, String source, String destination, String status, String seatNumber, String type,
			String flightName, String travellerName, int age, String vehicleNumber, String seatType) {
		super();
		this.bdate = bdate;
		this.source = source;
		this.destination = destination;
		this.status = status;
		this.seatNumber = seatNumber;
		this.type = type;
		this.flightName = flightName;
		this.travellerName = travellerName;
		this.age = age;
		this.vehicleNumber = vehicleNumber;
		this.seatType = seatType;
	}
	
	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getTravellerName() {
		return travellerName;
	}

	public void setTravellerName(String travellerName) {
		this.travellerName = travellerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	
	
}
