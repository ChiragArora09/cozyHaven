package com.group_3.cozyHaven.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class HolidayPackageDto {
	
	private int packageHotelId;
	
	private int holidayPackageId;
	
	private String fromLocation;
	
	private String toLocation;
	
	private String name;
	
	private String info;
	
	private String price;
	
	private Double discount;
	
	private String code;
	
	private String numActivities;
	
	private String hotelTransfer;
	
	private String meals;
	
	private String vehicleInfo;
	
	private String vehicleType;

	public HolidayPackageDto(int packageHotelId, int holidayPackageId, String fromLocation, String toLocation,Double discount, String code,
			String name, String info, String price, String numActivities,
			String hotelTransfer, String meals, String vehicleInfo, String vehicleType) {
		super();
		this.packageHotelId = packageHotelId;
		this.holidayPackageId = holidayPackageId;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.price = price;
		this.discount = discount;
		this.name = name;
		this.info = info;
		
		this.code = code;
		this.numActivities = numActivities;
		this.hotelTransfer = hotelTransfer;
		this.meals = meals;
		this.vehicleInfo = vehicleInfo;
		this.vehicleType = vehicleType;
	}

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

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
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

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getVehicleInfo() {
		return vehicleInfo;
	}

	public void setVehicleInfo(String vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}


	
	

	

}
