package com.group_3.cozyHaven.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.dto.MakePaymentDto;
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
	
	public FlightBooking addBooking(int custId, FlightBooking flightBooking) throws InputValidationException {
		Customer customer = customerService.getById(custId);
		flightBooking.setCustomer(customer);
		flightBooking.setStatus("Pending");
		flightBooking.setDate(LocalDate.now());
		return flightBookingRepository.save(flightBooking);
	}
	
	public FlightBooking getById(int bid) throws InputValidationException {
		Optional<FlightBooking> option = flightBookingRepository.findById(bid);
		if(option.isEmpty()) {
			throw new InputValidationException("Invalid ID");
		}
		return option.get();
	}

	public void makePayment(int bid, MakePaymentDto dto) {
		FlightBooking flightBooking = flightBookingRepository.findById(bid).get();
		flightBooking.setDiscount(dto.getDiscount());
		flightBooking.setAmount(dto.getAmount());
		flightBooking.setStatus("Confirmed");
		flightBooking.setDate(dto.getDateOfJourney());
		flightBookingRepository.save(flightBooking);
	}

}
