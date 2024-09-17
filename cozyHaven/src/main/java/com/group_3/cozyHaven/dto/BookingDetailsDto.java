package com.group_3.cozyHaven.dto;

import java.time.LocalDate;

import com.group_3.cozyHaven.enums.BookedStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class BookingDetailsDto {
	
	private LocalDate bookedDate;
    private LocalDate checkOutDate;
    private LocalDate checkInDate;
    private int numberOfRooms;
    private int numGuests;
    private double totalAmount;
    private String status;
    private String hotelName;
    private String location;
    
	public BookingDetailsDto(LocalDate bookedDate, LocalDate checkOutDate, LocalDate checkInDate, int numberOfRooms,
			int numGuests, double totalAmount, String status, String hotelName, String location) {
		super();
		this.bookedDate = bookedDate;
		this.checkOutDate = checkOutDate;
		this.checkInDate = checkInDate;
		this.numberOfRooms = numberOfRooms;
		this.numGuests = numGuests;
		this.totalAmount = totalAmount;
		this.status = status;
		this.hotelName = hotelName;
		this.location = location;
	}

	public LocalDate getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(LocalDate bookedDate) {
		this.bookedDate = bookedDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public int getNumGuests() {
		return numGuests;
	}

	public void setNumGuests(int numGuests) {
		this.numGuests = numGuests;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "BookingDetailsDto [bookedDate=" + bookedDate + ", checkOutDate=" + checkOutDate + ", checkInDate="
				+ checkInDate + ", numberOfRooms=" + numberOfRooms + ", numGuests=" + numGuests + ", totalAmount="
				+ totalAmount + ", status=" + status + ", hotelName=" + hotelName + ", location=" + location + "]";
	}
	
	

	
	
	
	
	

}
