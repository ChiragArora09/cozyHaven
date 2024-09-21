package com.group_3.cozyHaven.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Day {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	private int numDays;
	
	private String dayInfo;
	

	@ManyToOne
	private HolidayPackage holidayPackage;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getNumDays() {
		return numDays;
	}


	public void setNumDays(int numDays) {
		this.numDays = numDays;
	}


	public String getDayInfo() {
		return dayInfo;
	}


	public void setDayInfo(String dayInfo) {
		this.dayInfo = dayInfo;
	}


	public HolidayPackage getHolidayPackage() {
		return holidayPackage;
	}


	public void setHolidayPackage(HolidayPackage holidayPackage) {
		this.holidayPackage = holidayPackage;
	}
	
	
}
