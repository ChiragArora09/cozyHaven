package com.group_3.cozyHaven.dto;

public class ReviewDto {
	
	private String id;
	
	private String comments;
	
	private String rating;
	
	private String star;
	
	private String hotelName;
	
	private String location;

	public ReviewDto(String id, String comments, String rating, String star, String hotelName, String location) {
		super();
		this.id = id;
		this.comments = comments;
		this.rating = rating;
		this.star = star;
		this.hotelName = hotelName;
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	

}
