package com.group_3.cozyHaven.dto;

import java.time.LocalTime;


public class FlightBetweenStopsDto {
	
	private String flightName;
	
	private String flightNumber;
	
	private String flightDescription;
	
	private String source;
	
	private String destination;
	
	private LocalTime sourceArrival;
	
	private LocalTime destinationArrival;
		
	private int distance;
	
	private double amount;
	
	private int flightId;
	
	private int sourceStopNo;
	
	private int destinationStopNo;

	
	public FlightBetweenStopsDto(String flightName, String flightNumber, String flightDescription, String source,
			String destination, LocalTime sourceArrival, LocalTime destinationArrival, int distance, double amount,
			int flightId, int sourceStopNo, int destinationStopNo) {
		super();
		this.flightName = flightName;
		this.flightNumber = flightNumber;
		this.flightDescription = flightDescription;
		this.source = source;
		this.destination = destination;
		this.sourceArrival = sourceArrival;
		this.destinationArrival = destinationArrival;
		this.distance = distance;
		this.amount = amount;
		this.flightId = flightId;
		this.sourceStopNo = sourceStopNo;
		this.destinationStopNo = destinationStopNo;
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


	public String getFlightDescription() {
		return flightDescription;
	}


	public void setFlightDescription(String flightDescription) {
		this.flightDescription = flightDescription;
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


	public LocalTime getSourceArrival() {
		return sourceArrival;
	}


	public void setSourceArrival(LocalTime sourceArrival) {
		this.sourceArrival = sourceArrival;
	}


	public LocalTime getDestinationArrival() {
		return destinationArrival;
	}


	public void setDestinationArrival(LocalTime destinationArrival) {
		this.destinationArrival = destinationArrival;
	}


	public int getDistance() {
		return distance;
	}


	public void setDistance(int distance) {
		this.distance = distance;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public int getFlightId() {
		return flightId;
	}


	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}


	public int getSourceStopNo() {
		return sourceStopNo;
	}


	public void setSourceStopNo(int sourceStopNo) {
		this.sourceStopNo = sourceStopNo;
	}


	public int getDestinationStopNo() {
		return destinationStopNo;
	}


	public void setDestinationStopNo(int destinationStopNo) {
		this.destinationStopNo = destinationStopNo;
	}

}
