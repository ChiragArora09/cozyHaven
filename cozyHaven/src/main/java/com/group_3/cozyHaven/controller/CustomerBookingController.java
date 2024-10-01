package com.group_3.cozyHaven.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.service.CustomerBookingService;
import com.group_3.cozyHaven.utility.GetId;


@RestController
@RequestMapping("/my-bookings")
public class CustomerBookingController {
	
	@Autowired
	private GetId getId;
	
	@Autowired
	private CustomerBookingService customerBookingService;
	
	// GETTING CUSTOMER HISTORY
	@GetMapping("/bookings/{bookingType}/{bookingPeriod}") // booking period = past, upcoming, cancelled; booking type = flight, bus, hotel
	public ResponseEntity<?> getMyPastBookings(Principal principal, @PathVariable String bookingType, @PathVariable String bookingPeriod){
		String customerUsername = principal.getName();
		int customerId = getId.getIdByUsername(customerUsername);
		List<?> myBookings = customerBookingService.getMyBookings(customerId, bookingType, bookingPeriod);
		return ResponseEntity.ok(myBookings);
	}
	
	@DeleteMapping("/{bookingType}/upcoming/{bid}/delete")
	public ResponseEntity<?> cancelBooking(Principal principal, @PathVariable String bookingType, @PathVariable int bid) throws InputValidationException{
		String customerUsername = principal.getName();
		int customerId = getId.getIdByUsername(customerUsername);
		return ResponseEntity.ok(customerBookingService.cancelBooking(bookingType, bid, customerId));
	}
	
}
