package com.group_3.cozyHaven.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PackageBooking {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	private LocalDate dateBooked;

	@ManyToOne
	private HolidayPackage holidayPackage;
	
	@ManyToOne
	private Customer customer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDateBooked() {
		return dateBooked;
	}

	public void setDateBooked(LocalDate dateBooked) {
		this.dateBooked = dateBooked;
	}

	public HolidayPackage getHolidayPackage() {
		return holidayPackage;
	}

	public void setHolidayPackage(HolidayPackage holidayPackage) {
		this.holidayPackage = holidayPackage;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
}
