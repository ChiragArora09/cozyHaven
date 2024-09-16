package com.group_3.cozyHaven.model;

import com.group_3.cozyHaven.enums.BooleanType;
import com.group_3.cozyHaven.enums.IdType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Amenities {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Enumerated(EnumType.STRING)
	private BooleanType spa;
	
	@Enumerated(EnumType.STRING)
	private BooleanType gym;
	
	@Enumerated(EnumType.STRING)
	private BooleanType swimmingPool;
	
	@Enumerated(EnumType.STRING)
	private BooleanType parkingArea;
	
	@Enumerated(EnumType.STRING)
	private BooleanType freeWifi;
	
	@Enumerated(EnumType.STRING)
	private BooleanType breakfast;
	
	@Enumerated(EnumType.STRING)
	private BooleanType breafastLunc;
	
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BooleanType getSpa() {
		return spa;
	}

	public void setSpa(BooleanType spa) {
		this.spa = spa;
	}

	public BooleanType getGym() {
		return gym;
	}

	public void setGym(BooleanType gym) {
		this.gym = gym;
	}

	public BooleanType getSwimmingPool() {
		return swimmingPool;
	}

	public void setSwimmingPool(BooleanType swimmingPool) {
		this.swimmingPool = swimmingPool;
	}

	public BooleanType getParkingArea() {
		return parkingArea;
	}

	public void setParkingArea(BooleanType parkingArea) {
		this.parkingArea = parkingArea;
	}

	public BooleanType getFreeWifi() {
		return freeWifi;
	}

	public void setFreeWifi(BooleanType freeWifi) {
		this.freeWifi = freeWifi;
	}

	public BooleanType getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(BooleanType breakfast) {
		this.breakfast = breakfast;
	}

	public BooleanType getBreafastLunc() {
		return breafastLunc;
	}

	public void setBreafastLunc(BooleanType breafastLunc) {
		this.breafastLunc = breafastLunc;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "Amenities [id=" + id + ", spa=" + spa + ", gym=" + gym + ", swimmingPool=" + swimmingPool
				+ ", parkingArea=" + parkingArea + ", freeWifi=" + freeWifi + ", breakfast=" + breakfast
				+ ", breafastLunc=" + breafastLunc + ", room=" + room + "]";
	}
	
	
	
	

}
