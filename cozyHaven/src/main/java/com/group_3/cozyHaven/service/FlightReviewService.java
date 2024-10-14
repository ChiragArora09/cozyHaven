package com.group_3.cozyHaven.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.dto.ReviewInputDto;
import com.group_3.cozyHaven.dto.ReviewOnFlight;
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

	public List<ReviewOnFlight> getReviewsOnParticularFlight(int flightId) {
		List<Object[]> list = flightReviewRepository.getReviewsOnParticularFlight(flightId);
		List<ReviewOnFlight> reviewList = new ArrayList<>();
		for(Object[] obj : list) {
			int bookingId = (int) obj[0];
			String description = obj[1].toString();
			int rating = (int) obj[2];
			String date = obj[3].toString();
			String source = obj[4].toString();
			String destination = obj[5].toString();
			String customerName = obj[6].toString();
			String email = obj[7].toString();
			ReviewOnFlight reviewOnFlight = new ReviewOnFlight(bookingId, description, rating, date, source, destination, customerName, email);
			reviewList.add(reviewOnFlight);
		}
		return reviewList;
	}
	
}
