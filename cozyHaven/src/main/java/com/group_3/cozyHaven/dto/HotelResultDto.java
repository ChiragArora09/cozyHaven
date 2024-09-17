package com.group_3.cozyHaven.dto;

import java.time.LocalDate;

import com.group_3.cozyHaven.enums.BedType;
import com.group_3.cozyHaven.enums.BookedStatus;
import com.group_3.cozyHaven.enums.RoomType;

public class HotelResultDto {
	
	 private int roomId;
	    private String bedType;
	    private String roomType;
	    private double price;
	    private int availableRooms;
	    private String hotelName;
	    private String hotelLocation;
		public HotelResultDto(int roomId, String bedType, String roomType, double price, int availableRooms,
				String hotelName, String hotelLocation) {
			super();
			this.roomId = roomId;
			this.bedType = bedType;
			this.roomType = roomType;
			this.price = price;
			this.availableRooms = availableRooms;
			this.hotelName = hotelName;
			this.hotelLocation = hotelLocation;
		}
		public int getRoomId() {
			return roomId;
		}
		public void setRoomId(int roomId) {
			this.roomId = roomId;
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
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public int getAvailableRooms() {
			return availableRooms;
		}
		public void setAvailableRooms(int availableRooms) {
			this.availableRooms = availableRooms;
		}
		public String getHotelName() {
			return hotelName;
		}
		public void setHotelName(String hotelName) {
			this.hotelName = hotelName;
		}
		public String getHotelLocation() {
			return hotelLocation;
		}
		public void setHotelLocation(String hotelLocation) {
			this.hotelLocation = hotelLocation;
		}
		
	    
	    
}