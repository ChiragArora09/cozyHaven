package com.group_3.cozyHaven.dto;

import java.time.LocalTime;

public class FlightCityInputDto {
	private Integer id;
	private LocalTime arrival;
	private LocalTime departure;
	private int distance;
	private int stop_number;
	private int city_id;
	
	public FlightCityInputDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FlightCityInputDto(LocalTime arrival, LocalTime departure, int distance, int stop_number, int city_id) {
		super();
		this.arrival = arrival;
		this.departure = departure;
		this.distance = distance;
		this.stop_number = stop_number;
		this.city_id = city_id;
	}

	public FlightCityInputDto(Integer id, LocalTime arrival, LocalTime departure, int distance, int stop_number,
			int city_id) {
		super();
		this.id = id;
		this.arrival = arrival;
		this.departure = departure;
		this.distance = distance;
		this.stop_number = stop_number;
		this.city_id = city_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalTime getArrival() {
		return arrival;
	}

	public void setArrival(LocalTime arrival) {
		this.arrival = arrival;
	}

	public LocalTime getDeparture() {
		return departure;
	}

	public void setDeparture(LocalTime departure) {
		this.departure = departure;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getStop_number() {
		return stop_number;
	}

	public void setStop_number(int stopNumber) {
		this.stop_number = stopNumber;
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	
}
