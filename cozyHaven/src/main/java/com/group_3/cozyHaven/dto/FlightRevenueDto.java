package com.group_3.cozyHaven.dto;

public class FlightRevenueDto {
	private double revenue;
	private String flightInfo;
	
	public FlightRevenueDto(double revenue, String flightInfo) {
		super();
		this.revenue = revenue;
		this.flightInfo = flightInfo;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	public String getFlightInfo() {
		return flightInfo;
	}

	public void setFlightInfo(String flightInfo) {
		this.flightInfo = flightInfo;
	}
	
	
}
