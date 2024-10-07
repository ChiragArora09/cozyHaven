package com.group_3.cozyHaven.model;

import com.group_3.cozyHaven.enums.BooleanType;
import com.group_3.cozyHaven.enums.OfferType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "flight_offer")
public class FlightOffer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String description;
	
	@Enumerated(EnumType.STRING)
	private OfferType offerType;
	
	private int offerDiscount;
	
	private double offerCondition;
	
	@ManyToOne
	private Flight flight;
	
	private int loyaltyPoints;
	
	@Enumerated(EnumType.STRING)
	private BooleanType active = BooleanType.YES;
	
	public FlightOffer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FlightOffer(int id, String description, OfferType offerType, int offerDiscount, double offerCondition,
			Flight flight, int loyaltyPoints) {
		super();
		this.id = id;
		this.description = description;
		this.offerType = offerType;
		this.offerDiscount = offerDiscount;
		this.offerCondition = offerCondition;
		this.flight = flight;
		this.loyaltyPoints = loyaltyPoints;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OfferType getOfferType() {
		return offerType;
	}

	public void setOfferType(OfferType offerType) {
		this.offerType = offerType;
	}

	public int getOfferDiscount() {
		return offerDiscount;
	}

	public void setOfferDiscount(int offerDiscount) {
		this.offerDiscount = offerDiscount;
	}

	public double getOfferCondition() {
		return offerCondition;
	}

	public void setOfferCondition(double offerCondition) {
		this.offerCondition = offerCondition;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}
	
	public BooleanType getActive() {
		return active;
	}

	public void setActive(BooleanType active) {
		this.active = active;
	}
	
}
