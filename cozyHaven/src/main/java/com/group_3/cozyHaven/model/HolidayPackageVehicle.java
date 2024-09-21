package com.group_3.cozyHaven.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class HolidayPackageVehicle {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	private String destination;
	
	private LocalDateTime depatureTime;
	
	@ManyToOne
	private HolidayPackage holidayPackage;
	
	@ManyToOne
	private PackageVehicle packageVehicle;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDateTime getDepatureTime() {
		return depatureTime;
	}

	public void setDepatureTime(LocalDateTime depatureTime) {
		this.depatureTime = depatureTime;
	}

	public HolidayPackage getHolidayPackage() {
		return holidayPackage;
	}

	public void setHolidayPackage(HolidayPackage holidayPackage) {
		this.holidayPackage = holidayPackage;
	}

	public PackageVehicle getPackageVehicle() {
		return packageVehicle;
	}

	public void setPackageVehicle(PackageVehicle packageVehicle) {
		this.packageVehicle = packageVehicle;
	}
	
	
}