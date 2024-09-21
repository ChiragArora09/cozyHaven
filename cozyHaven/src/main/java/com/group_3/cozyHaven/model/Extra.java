package com.group_3.cozyHaven.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Extra {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	private String hotelTransfer;
	
	private String meals;
	
	private String numActivities;
	
	@ManyToOne
	private HolidayPackage holidayPackage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMeals() {
		return meals;
	}

	public void setMeals(String meals) {
		this.meals = meals;
	}

	public String getNumActivities() {
		return numActivities;
	}

	public void setNumActivities(String numActivities) {
		this.numActivities = numActivities;
	}

	public String getHotelTransfer() {
		return hotelTransfer;
	}

	public void setHotelTransfer(String hotelTransfer) {
		this.hotelTransfer = hotelTransfer;
	}

	public HolidayPackage getHolidayPackage() {
		return holidayPackage;
	}

	public void setHolidayPackage(HolidayPackage holidayPackage) {
		this.holidayPackage = holidayPackage;
	}
	
	

}
