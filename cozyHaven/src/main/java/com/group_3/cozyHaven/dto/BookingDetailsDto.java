package com.group_3.cozyHaven.dto;

import java.time.LocalDate;

import com.group_3.cozyHaven.enums.BookedStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class BookingDetailsDto {
	private int bid;
	private LocalDate bookedDate;
    private String checkOutDate;
    private LocalDate checkInDate;
    private int numberOfRooms;
    private int numGuests;
    private String totalAmount;
    private String status;
    private int hotelId;
    private String hotelName;
    private String location;
	public BookingDetailsDto(int bid, LocalDate bookedDate, String checkOutDate, LocalDate checkInDate,
			int numberOfRooms, int numGuests, String totalAmount, String status,int hotelId, String hotelName, String location) {
		super();
		this.bid = bid;
		this.bookedDate = bookedDate;
		this.checkOutDate = checkOutDate;
		this.checkInDate = checkInDate;
		this.numberOfRooms = numberOfRooms;
		this.numGuests = numGuests;
		this.totalAmount = totalAmount;
		this.status = status;
		this.hotelId=hotelId;
		this.hotelName = hotelName;
		this.location = location;
	}
	public BookingDetailsDto(LocalDate bookedDate, String checkOutDate, LocalDate checkInDate, String status,
			String hotelName, String location) {
		super();
		this.bookedDate = bookedDate;
		this.checkOutDate = checkOutDate;
		this.checkInDate = checkInDate;
		this.status = status;
		this.hotelName = hotelName;
		this.location = location;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public LocalDate getBookedDate() {
		return bookedDate;
	}
	public void setBookedDate(LocalDate bookedDate) {
		this.bookedDate = bookedDate;
	}
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
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
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
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
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	
    
	
	

}
