package com.group_3.cozyHaven.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class HotelExtra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String cancellationInfo;
	
	private String complimentary;
	
	@ManyToOne
	private Room room;

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getCancellationInfo() {
		return cancellationInfo;
	}

	public void setCancellationInfo(String cancellationInfo) {
		this.cancellationInfo = cancellationInfo;
	}

	public String getComplimentary() {
		return complimentary;
	}

	public void setComplimentary(String complimentary) {
		this.complimentary = complimentary;
	}
	
	

}
