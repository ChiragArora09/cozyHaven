package com.group_3.cozyHaven.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class FlightLoyaltyPoints {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Customer customer;
	
	@ManyToOne
	private ServiceProvider serviceProvider;
	
	private int earned;
	
	private int spent;
	
	private int total;
	
	private LocalDate transactionDate;
	
	public FlightLoyaltyPoints(Customer customer, ServiceProvider serviceProvider, int earned, int spent, int total,
			LocalDate transactionDate) {
		super();
		this.customer = customer;
		this.serviceProvider = serviceProvider;
		this.earned = earned;
		this.spent = spent;
		this.total = total;
		this.transactionDate = transactionDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public int getEarned() {
		return earned;
	}

	public void setEarned(int earned) {
		this.earned = earned;
	}

	public int getSpent() {
		return spent;
	}

	public void setSpent(int spent) {
		this.spent = spent;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	};
	
}
