package com.group_3.cozyHaven.model;

import com.group_3.cozyHaven.enums.BusSeatType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class BusSeat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String seatNumber;
	
	@Enumerated(EnumType.STRING)
	private BusSeatType seatType;
	
	@ManyToOne
	private Bus bus;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public BusSeatType getSeatType() {
		return seatType;
	}

	public void setSeatType(BusSeatType seatType) {
		this.seatType = seatType;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}
	
}
