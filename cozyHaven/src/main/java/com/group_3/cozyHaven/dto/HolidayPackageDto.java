package com.group_3.cozyHaven.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class HolidayPackageDto {
	
	private int packageHotelId;
	
	private int holidayPackageId;
	
	private String location;
	
	private String name;
	
	private String info;
	
	private String price;
	
	private String numActivities;
	
	private String hotelTransfer;
	
	private String meals;


	public int getPackageHotelId() {
		return packageHotelId;
	}

	public void setPackageHotelId(int packageHotelId) {
		this.packageHotelId = packageHotelId;
	}

	

	public int getHolidayPackageId() {
		return holidayPackageId;
	}

	public void setHolidayPackageId(int holidayPackageId) {
		this.holidayPackageId = holidayPackageId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

	public String getMeals() {
		return meals;
	}

	public void setMeals(String meals) {
		this.meals = meals;
	}

	public HolidayPackageDto(int packageHotelId, int holidayPackageId, String location, String name, String info,
			String price, String numActivities, String hotelTransfer, String meals) {
		super();
		this.packageHotelId = packageHotelId;
		this.holidayPackageId = holidayPackageId;
		this.location = location;
		this.name = name;
		this.info = info;
		this.price = price;
		this.numActivities = numActivities;
		this.hotelTransfer = hotelTransfer;
		this.meals = meals;
	}


	

	

}
