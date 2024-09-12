package com.group_3.cozyHaven.dto;

import java.time.LocalTime;

public class FlightBetweenStopsDto {
	
	private String flightName;
	
	private String flightNumber;
	
	private String source;
	
	private String destination;
	
	private LocalTime sourceDeparture;
	
	private LocalTime destinationArrival;
	
	private double amount;

	public FlightBetweenStopsDto(String flightName, String flightNumber, String source, String destination,
			LocalTime sourceDeparture, LocalTime destinationArrival, double amount) {
		super();
		this.flightName = flightName;
		this.flightNumber = flightNumber;
		this.source = source;
		this.destination = destination;
		this.sourceDeparture = sourceDeparture;
		this.destinationArrival = destinationArrival;
		this.amount = amount;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}

// select f.name, f.number, r.source, r.destination, fr.source_departure, fr.destination_arrival, fr.amount from flight f JOIN flight_route fr ON 
// fr.flight_id=f.id JOIN route r ON r.id=fr.route_id JOIN flight_class fc ON f.id=fc.flight_id WHERE r.source="Mumbai" AND r.destination="Delhi" 
// AND fc.type="BUSINESS" group by f.id;