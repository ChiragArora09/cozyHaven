package com.group_3.cozyHaven.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	@GetMapping("/bookings/{bookingPeriod}") // booking period = past, upcoming, cancelled; booking type = flight, bus, hotel
	public ResponseEntity<?> getMyPastBookings(Principal principal, @RequestBody String bookingType, @PathVariable String bookingPeriod){
		String customerUsername = principal.getName();
		int customerId = getId.getIdByUsername(customerUsername);
		List<?> myBookings = customerBookingService.getMyBookings(customerId, bookingType, bookingPeriod);
		return ResponseEntity.ok(myBookings);
	}
	
}
