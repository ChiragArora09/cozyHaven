package com.group_3.cozyHaven.dto;

public class PopularFlightsDto {
	private long bookingCount;
	private String flightInfo;
	
	public PopularFlightsDto(long bookingCount, String flightInfo) {
		super();
		this.bookingCount = bookingCount;
		this.flightInfo = flightInfo;
	}

	public long getBookingCount() {
		return bookingCount;
	}

	public void setBookingCount(long bookingCount) {
		this.bookingCount = bookingCount;
	}

	public String getFlightInfo() {
		return flightInfo;
	}

	public void setFlightInfo(String flightInfo) {
		this.flightInfo = flightInfo;
	}
	
}
