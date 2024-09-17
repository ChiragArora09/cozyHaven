package com.group_3.cozyHaven.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.BusBooking;
import com.group_3.cozyHaven.model.Customer;
import com.group_3.cozyHaven.repository.BusBookingRepository;

@Service
public class BusBookingService {
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	private BusBookingRepository busBookingRepository;
	
	public BusBooking addBooking(int cust_id, int busId, BusBooking busBooking) throws InputValidationException {
		Customer customer = customerService.getById(cust_id);
		busBooking.setCustomer(customer);
		busBooking.setStatus("Pending");
		return busBookingRepository.save(busBooking);
	}
	
	public BusBooking getById(int bid) throws InputValidationException {
		Optional<BusBooking> option = busBookingRepository.findById(bid);
		if(option.isEmpty()) {
			throw new InputValidationException("Invalid ID");
		}
		return option.get();	
	}

}
