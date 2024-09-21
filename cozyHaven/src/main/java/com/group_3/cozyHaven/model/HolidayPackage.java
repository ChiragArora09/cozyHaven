package com.group_3.cozyHaven.model;

import org.apache.logging.log4j.util.StringBuilderFormattable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class HolidayPackage {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	private String fromLocation;
	
	private String toLocation;
	
	private String type;
	
	private int numGuests;
	
	private int numDays;
	
	@ManyToOne
	private PackageHotel packageHotel;

	public PackageHotel getPackageHotel() {
		return packageHotel;
	}

	public void setPackageHotel(PackageHotel packageHotel) {
		this.packageHotel = packageHotel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNumGuests() {
		return numGuests;
	}

	public void setNumGuests(int numGuests) {
		this.numGuests = numGuests;
	}

	public int getNumDays() {
		return numDays;
	}

	public void setNumDays(int numDays) {
		this.numDays = numDays;
	}
	
	
	
	
	
	
	

}
