package com.group_3.cozyHaven.model;

import java.util.List;

import com.group_3.cozyHaven.enums.BedType;
import com.group_3.cozyHaven.enums.RoomStatus;
import com.group_3.cozyHaven.enums.RoomType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="hotel_room_type")
public class Room {
	
	@Id
	@GeneratedValue
	private int id;
	
	private int capacity;
	
	private double price;
	
	@Enumerated(EnumType.STRING)
	private RoomType roomType;
	
	@Enumerated(EnumType.STRING)
	private BedType bedType;
	
	private int totalRooms;
	
	private int bookedRooms;
	
	@ManyToOne
	private Hotel hotel;
	
	@OneToMany
	private List<Image> images;
	
	@OneToMany
	private List<Amenities> amenities;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public BedType getBedType() {
		return bedType;
	}

	public void setBedType(BedType bedType) {
		this.bedType = bedType;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public int getTotalRooms() {
		return totalRooms;
	}

	public void setTotalRooms(int totalRooms) {
		this.totalRooms = totalRooms;
	}

	public int getBookedRooms() {
		return bookedRooms;
	}

	public void setBookedRooms(int bookedRooms) {
		this.bookedRooms = bookedRooms;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	
	
	public List<Amenities> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<Amenities> amenities) {
		this.amenities = amenities;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", capacity=" + capacity + ", price=" + price + ", roomType=" + roomType
				+ ", bedType=" + bedType + ", totalRooms=" + totalRooms + ", bookedRooms=" + bookedRooms + ", hotel="
				+ hotel + ", images=" + images + ", amenities=" + amenities + "]";
	}

	

	
	
	
	
	

}
