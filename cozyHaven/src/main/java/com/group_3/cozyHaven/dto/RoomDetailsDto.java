package com.group_3.cozyHaven.dto;

public class RoomDetailsDto {
	
	private String hotelName;
	
	private String bedType;
	
	private String roomType;
	
	private String price;
	
	private String breakFastLunch;
	
	private String breakFast;
	
	private String freeWifi;
	
	private String gym;
	
	private String parkingArea;
	
	private String spa;
	
	private String swimmingPool;
	
	
	
	
	
	private String cancellationInfo;
	
	private String complimentary;
	
	private String description;
	
	

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getBedType() {
		return bedType;
	}

	public void setBedType(String bedType) {
		this.bedType = bedType;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBreakFastLunch() {
		return breakFastLunch;
	}

	public void setBreakFastLunch(String breakFastLunch) {
		this.breakFastLunch = breakFastLunch;
	}

	public String getBreakFast() {
		return breakFast;
	}

	public void setBreakFast(String breakFast) {
		this.breakFast = breakFast;
	}

	public String getFreeWifi() {
		return freeWifi;
	}

	public void setFreeWifi(String freeWifi) {
		this.freeWifi = freeWifi;
	}

	public String getGym() {
		return gym;
	}

	public void setGym(String gym) {
		this.gym = gym;
	}

	public String getParkingArea() {
		return parkingArea;
	}

	public void setParkingArea(String parkingArea) {
		this.parkingArea = parkingArea;
	}

	public String getSpa() {
		return spa;
	}

	public void setSpa(String spa) {
		this.spa = spa;
	}

	public String getSwimmingPool() {
		return swimmingPool;
	}

	public void setSwimmingPool(String swimmingPool) {
		this.swimmingPool = swimmingPool;
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

	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RoomDetailsDto(String hotelName, String bedType, String roomType, String price, String breakFastLunch,
			String breakFast, String freeWifi, String gym, String parkingArea, String spa, String swimmingPool,
			 String cancellationInfo, String complimentary,String description) {
		super();
		this.hotelName = hotelName;
		this.bedType = bedType;
		this.roomType = roomType;
		this.price = price;
		this.breakFastLunch = breakFastLunch;
		this.breakFast = breakFast;
		this.freeWifi = freeWifi;
		this.gym = gym;
		this.parkingArea = parkingArea;
		this.spa = spa;
		this.swimmingPool = swimmingPool;
		
		this.cancellationInfo = cancellationInfo;
		this.complimentary = complimentary;
		this.description=description;
	}

	
}
