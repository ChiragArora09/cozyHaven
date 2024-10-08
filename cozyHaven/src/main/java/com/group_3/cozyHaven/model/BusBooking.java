package com.group_3.cozyHaven.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class BusBooking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private LocalDate date;
	
	private String source;
	
	private String destination;
	
	private String status;
	
	private int sourceStopNumber;
	
	private int destinationStopNumber;
	
	private double amount;
	
	@ManyToOne
	private Customer customer;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getSourceStopNumber() {
		return sourceStopNumber;
	}

	public void setSourceStopNumber(int sourceStopNumber) {
		this.sourceStopNumber = sourceStopNumber;
	}

	public int getDestinationStopNumber() {
		return destinationStopNumber;
	}

	public void setDestinationStopNumber(int destinationStopNumber) {
		this.destinationStopNumber = destinationStopNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
