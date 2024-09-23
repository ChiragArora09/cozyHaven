package com.group_3.cozyHaven.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.BusBooking;
import com.group_3.cozyHaven.model.Customer;
import com.group_3.cozyHaven.repository.BusBookingRepository;

@Service
public class BusBookingService {
	
	private Logger logger = LoggerFactory.getLogger(BusBookingService.class);
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	private BusBookingRepository busBookingRepository;
	
	public BusBooking addBooking(int cust_id, int busId, BusBooking busBooking) throws InputValidationException {
		Customer customer = customerService.getById(cust_id);
		busBooking.setCustomer(customer);
		busBooking.setStatus("Pending");
		logger.info("Initializing Bus booking for " + customer.getFullname() + " booking id " + busBooking.getId());
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
