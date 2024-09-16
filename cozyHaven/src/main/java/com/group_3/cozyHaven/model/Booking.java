package com.group_3.cozyHaven.model;

import java.time.LocalDate;

import com.group_3.cozyHaven.enums.BookedStatus;
import com.group_3.cozyHaven.enums.BooleanType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="booking_room")
public class Booking {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	@ManyToOne
	private Customer customer;
	
	@ManyToOne
	private Room room;
	
	private LocalDate checkOutDate;
	
	private LocalDate checkInDate;
	
	private int numberOfRooms;
	
	private int numGuests;
	
	private double totalAmount;
	
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Enumerated(EnumType.STRING)
	private BookedStatus status;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
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

	public BookedStatus getStatus() {
		return status;
	}

	public void setStatus(BookedStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", customer=" + customer + ", room=" + room + ", checkOutDate=" + checkOutDate
				+ ", checkInDate=" + checkInDate + ", numberOfRooms=" + numberOfRooms + ", numGuests=" + numGuests
				+ ", status=" + status + "]";
	}

	

    
	
	
	
	
}
