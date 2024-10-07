package com.group_3.cozyHaven.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class FlightReview {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String description;
	
	private int star;
	
	@ManyToOne
	private FlightBooking flightBooking;
	
	@ManyToOne
	private Customer customer;

	
	public FlightReview(String description, int star, FlightBooking flightBooking, Customer customer) {
		super();
		this.description = description;
		this.star = star;
		this.flightBooking = flightBooking;
		this.customer = customer;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getStar() {
		return star;
	}


	public void setStar(int star) {
		this.star = star;
	}


	public FlightBooking getFlightBooking() {
		return flightBooking;
	}


	public void setFlightBooking(FlightBooking flightBooking) {
		this.flightBooking = flightBooking;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

}
