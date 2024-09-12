package com.group_3.cozyHaven.model;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "flight_route")
public class FlightRoute {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private LocalTime sourceDeparture;
	
	private LocalTime destinationArrival;
	
	private Double amount;
	
	@ManyToOne
	private Flight flight;
	
	@ManyToOne
	private Route route;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalTime getSourceDeparture() {
		return sourceDeparture;
	}

	public void setSourceDeparture(LocalTime sourceDeparture) {
		this.sourceDeparture = sourceDeparture;
	}

	public LocalTime getDestinationArrival() {
		return destinationArrival;
	}

	public void setDestinationArrival(LocalTime destinationArrival) {
		this.destinationArrival = destinationArrival;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}
	
}
