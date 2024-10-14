package com.group_3.cozyHaven.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class FlightBooking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private LocalDate date;
	
	private String source;
	
	private String destination;
	
	private String status;
	
	private int sourceCityNumber;
	
	private int destinationCityNumber;
	
	private double amount;
	
	private double discount = 0;
	
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

	public int getSourceCityNumber() {
		return sourceCityNumber;
	}

	public void setSourceCityNumber(int sourceCityNumber) {
		this.sourceCityNumber = sourceCityNumber;
	}

	public int getDestinationCityNumber() {
		return destinationCityNumber;
	}

	public void setDestinationCityNumber(int destinationCityNumber) {
		this.destinationCityNumber = destinationCityNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
}
