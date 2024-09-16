package com.group_3.cozyHaven.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "flight_seat_booking")
public class FlightSeatBooking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private FlightBooking flightBooking;
	
	@ManyToOne
	private FlightSeat flightSeat;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FlightBooking getFlightBooking() {
		return flightBooking;
	}

	public void setFlightBooking(FlightBooking flightBooking) {
		this.flightBooking = flightBooking;
	}

	public FlightSeat getFlightSeat() {
		return flightSeat;
	}

	public void setFlightSeat(FlightSeat flightSeat) {
		this.flightSeat = flightSeat;
	}

}
