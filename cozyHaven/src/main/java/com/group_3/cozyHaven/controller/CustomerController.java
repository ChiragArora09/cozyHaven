package com.group_3.cozyHaven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.dto.MessageDto;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Booking;
import com.group_3.cozyHaven.model.Customer;
import com.group_3.cozyHaven.service.BookingService;
import com.group_3.cozyHaven.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/add")
public ResponseEntity<?> addEmployee(@RequestBody Customer customer,MessageDto dto) { //reading the i/p
		
		try {
			customerService.validate(customer);
		} catch (InputValidationException e) {
			dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto); 
		} 


		return ResponseEntity.ok(customerService.addCustomer(customer)); 
	
	}
	
}
