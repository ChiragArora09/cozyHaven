package com.group_3.cozyHaven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.dto.ReviewInputDto;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.Customer;
import com.group_3.cozyHaven.model.FlightBooking;
import com.group_3.cozyHaven.model.FlightReview;
import com.group_3.cozyHaven.repository.FlightReviewRepository;

@Service
public class FlightReviewService {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private FlightBookingService flightBookingService;
	
	@Autowired
	private FlightReviewRepository flightReviewRepository;

	public Object addFeedback(int customerId, ReviewInputDto dto) throws InputValidationException {
		Customer customer = customerService.getById(customerId);
		FlightBooking flightBooking = flightBookingService.getById(dto.getBookingId());
		int start = dto.getStars();
		String description = dto.getDescription();
		
		FlightReview flightReview = new FlightReview(description, start, flightBooking, customer);
		
		return flightReviewRepository.save(flightReview);
	}
	
}
