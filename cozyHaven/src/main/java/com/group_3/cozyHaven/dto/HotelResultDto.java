package com.group_3.cozyHaven.dto;

import java.time.LocalDate;

import com.group_3.cozyHaven.enums.BedType;
import com.group_3.cozyHaven.enums.BookedStatus;
import com.group_3.cozyHaven.enums.RoomType;

public class HotelResultDto {
	
	 private int roomId;
	    private String roomType;
	    private String price;
	    private String hotelName;
	    private String hotelLocation;
        private int hotelId;
		public HotelResultDto(int roomId, String roomType, String price, String hotelName, String hotelLocation,
				int hotelId) {
			super();
			this.roomId = roomId;
			this.roomType = roomType;
			this.price = price;
			this.hotelName = hotelName;
			this.hotelLocation = hotelLocation;
			this.hotelId = hotelId;
		}
		public int getRoomId() {
			return roomId;
		}
		public void setRoomId(int roomId) {
			this.roomId = roomId;
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
		public int getHotelId() {
			return hotelId;
		}
		public void setHotelId(int hotelId) {
			this.hotelId = hotelId;
		}
		
	
	    
}