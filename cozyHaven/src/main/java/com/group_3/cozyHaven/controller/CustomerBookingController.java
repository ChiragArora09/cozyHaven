package com.group_3.cozyHaven.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.dto.ReviewInputDto;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.service.CustomerBookingService;
import com.group_3.cozyHaven.service.FlightReviewService;
import com.group_3.cozyHaven.utility.GetId;



@RestController
@RequestMapping("/my-bookings")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CustomerBookingController {
	
	@Autowired
	private GetId getId;
	
	@Autowired
	private CustomerBookingService customerBookingService;
	
	@Autowired
	private FlightReviewService flightReviewService;
	
	// GETTING CUSTOMER HISTORY
	@GetMapping("/bookings/{bookingType}/{bookingPeriod}") // booking period = past, upcoming, cancelled; booking type = flight, bus, hotel
	public ResponseEntity<?> getMyPastBookings(Principal principal, @PathVariable String bookingType, @PathVariable String bookingPeriod){
		String customerUsername = principal.getName();
		int customerId = getId.getIdByUsername(customerUsername);
		List<?> myBookings = customerBookingService.getMyBookings(customerId, bookingType, bookingPeriod);
		return ResponseEntity.ok(myBookings);
	}
	
	// CANCEL BOOKING
	@PostMapping("/{bookingType}/upcoming/{bid}/delete")
	public void cancelBooking(@PathVariable String bookingType, @PathVariable int bid) throws InputValidationException{
		customerBookingService.cancelBooking(bookingType, bid);
	}
	
	// SUBMIT FEEDBACK
	@PostMapping("/submit-feedback")
	public ResponseEntity<?> submitFeedback(Principal principal, @RequestBody ReviewInputDto dto) throws InputValidationException{
		String customerUsername = principal.getName();
		int customerId = getId.getIdByUsername(customerUsername);
		return ResponseEntity.ok(flightReviewService.addFeedback(customerId, dto));
	}
	
	@GetMapping("/generated-offers/{bid}")
	public List<?> generateOffers(Principal principal, @PathVariable int bid) {
		String customerUsername = principal.getName();
		int customerId = getId.getIdByUsername(customerUsername);
		return customerBookingService.generateOffers(customerId, bid);
	}
	
	
	
}
