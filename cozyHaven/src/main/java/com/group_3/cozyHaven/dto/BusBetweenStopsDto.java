package com.group_3.cozyHaven.dto;

import java.time.LocalTime;

import com.group_3.cozyHaven.enums.BusType;

public class BusBetweenStopsDto {
	
	private String busName;
	
	private String busNumber;
	
	private BusType busType;
	
	private String busDescription;
	
	private String source;
	
	private String destination;
	
	private LocalTime sourceArrival;
	
	private LocalTime destinationArrival;
		
	private int distance;
	
	private double amount;

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}

	public BusType getBusType() {
		return busType;
	}

	public void setBusType(BusType busType) {
		this.busType = busType;
	}

	public String getBusDescription() {
		return busDescription;
	}

	public void setBusDescription(String busDescription) {
		this.busDescription = busDescription;
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

}
