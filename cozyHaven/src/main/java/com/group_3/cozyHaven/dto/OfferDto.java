package com.group_3.cozyHaven.dto;

public class OfferDto {
	private int id;
	private String description;
	private String offerType;
	private int offerDiscount;
	private double offerCondition;
	private int loyaltyPoints;
	
	public OfferDto(int id, String description, String offerType, int offerDiscount, double offerCondition,
			int loyaltyPoints) {
		super();
		this.id = id;
		this.description = description;
		this.offerType = offerType;
		this.offerDiscount = offerDiscount;
		this.offerCondition = offerCondition;
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

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
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

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}
	
}
