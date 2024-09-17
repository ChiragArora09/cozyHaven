package com.group_3.cozyHaven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.BusBooking;
import com.group_3.cozyHaven.model.BusPassenger;
import com.group_3.cozyHaven.repository.BusPassengerRepository;

@Service
public class BusPassengerService {
	
	@Autowired
	private BusBookingService busBookingService;
	
	@Autowired
	private BusPassengerRepository busPassengerRepository;

	public BusPassenger addPassenger(int bid, BusPassenger passenger) throws InputValidationException {
		BusBooking busBooking = busBookingService.getById(bid);
		passenger.setBusBooking(busBooking);
		return busPassengerRepository.save(passenger);
	}

}
