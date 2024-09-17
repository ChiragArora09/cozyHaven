package com.group_3.cozyHaven.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bus_seat_booking")
public class BusSeatBooking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private BusSeat busSeat;
	
	@ManyToOne
	private BusBooking busBooking;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BusSeat getBusSeat() {
		return busSeat;
	}

	public void setBusSeat(BusSeat busSeat) {
		this.busSeat = busSeat;
	}

	public BusBooking getBusBooking() {
		return busBooking;
	}

	public void setBusBooking(BusBooking busBooking) {
		this.busBooking = busBooking;
	}
	
}
