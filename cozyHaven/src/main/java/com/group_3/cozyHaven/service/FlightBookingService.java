package com.group_3.cozyHaven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.Customer;
import com.group_3.cozyHaven.model.FlightBooking;
import com.group_3.cozyHaven.repository.FlightBookingRepository;

@Service
public class FlightBookingService {
	
	@Autowired
	private FlightBookingRepository flightBookingRepository;

	@Autowired
	private CustomerService customerService;
	
	public FlightBooking addBooking(int cid, int fid, FlightBooking flightBooking) throws InputValidationException {
		Customer customer = customerService.getById(cid);
		flightBooking.setCustomer(customer);
		flightBooking.setStatus("Pending");
		return flightBookingRepository.save(flightBooking);
	}

}
