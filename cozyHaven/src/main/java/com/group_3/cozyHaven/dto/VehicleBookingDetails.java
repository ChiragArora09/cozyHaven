package com.group_3.cozyHaven.dto;

import java.time.LocalDate;


public class VehicleBookingDetails {
	private int bookingId;
	private LocalDate booking;
	private String source; 
	private String destination;
	private String classtype; // or busType
	private String vehicleName;
	private String vehicleNumber;
	private double amount;
	private String status;
	
	
	
	public VehicleBookingDetails(int bookingId, LocalDate booking, String source, String destination,
			String vehicleName, String vehicleNumber, double amount, String status) {
		super();
		this.bookingId = bookingId;
		this.booking = booking;
		this.source = source;
		this.destination = destination;
		this.vehicleName = vehicleName;
		this.vehicleNumber = vehicleNumber;
		this.amount = amount;
		this.status = status;
	}


	public VehicleBookingDetails(int bookingId, LocalDate booking, String source, String destination, String classtype,
			String vehicleName, String vehicleNumber, double amount, String status) {
		super();
		this.bookingId = bookingId;
		this.booking = booking;
		this.source = source;
		this.destination = destination;
		this.classtype = classtype;
		this.vehicleName = vehicleName;
		this.vehicleNumber = vehicleNumber;
		this.amount = amount;
		this.status = status;
	}

	
	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDate getBooking() {
		return booking;
	}

	public void setBooking(LocalDate booking) {
		this.booking = booking;
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

	public String getClasstype() {
		return classtype;
	}

	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
