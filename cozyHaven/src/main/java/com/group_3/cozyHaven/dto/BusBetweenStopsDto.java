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
	
	private int busId;
	
	private int sourceStopNo;
	
	private int destinationStopNo;
	

	public BusBetweenStopsDto(String busName, String busNumber, BusType busType, String busDescription, String source,
			String destination, LocalTime sourceArrival, LocalTime destinationArrival, int distance, double amount, int busId, int sourceStopNo, int destinationStopNo) {
		super();
		this.busName = busName;
		this.busNumber = busNumber;
		this.busType = busType;
		this.busDescription = busDescription;
		this.source = source;
		this.destination = destination;
		this.sourceArrival = sourceArrival;
		this.destinationArrival = destinationArrival;
		this.distance = distance;
		this.amount = amount;
		this.busId = busId;
		this.sourceStopNo = sourceStopNo;
		this.destinationStopNo = destinationStopNo;
	}

	
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

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
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
