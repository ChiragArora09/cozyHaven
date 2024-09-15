package com.group_3.cozyHaven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.FlightBooking;
import com.group_3.cozyHaven.model.FlightTraveller;
import com.group_3.cozyHaven.repository.FlightTravellerRepository;

@Service
public class FlightTravellerService {
	
	@Autowired
	private FlightBookingService flightBookingService;
	
	@Autowired
	private FlightTravellerRepository flightTravellerRepository;

	public FlightTraveller addTraveller(int bid, FlightTraveller flightTraveller) throws InputValidationException {
		FlightBooking flightBooking = flightBookingService.getById(bid);
		System.out.println(flightBooking);
		flightTraveller.setFlightBooking(flightBooking);
		return flightTravellerRepository.save(flightTraveller);
	}

}
