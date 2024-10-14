package com.group_3.cozyHaven.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.dto.BookingDetailsDto;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.exception.RoomUnavailableException;
import com.group_3.cozyHaven.model.Booking;
import com.group_3.cozyHaven.service.BookingService;
import com.group_3.cozyHaven.utility.GetId;


@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = {"http://localhost:4200"})
public class BookingController {
	
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private GetId getId;
	
	@PostMapping("/room/{roomId}")
	public ResponseEntity<?> reviewBooking(@PathVariable int roomId,Principal principal,@RequestBody Booking booking) {
		
		try {
			String username=principal.getName();
			int customerId=getId.getIdByUsername(username);
			Booking book=bookingService.bookRoom(roomId,customerId,booking);
			return ResponseEntity.ok(book);
		} catch (InvalidIdException | RoomUnavailableException | InputValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} 
	}
	
	@GetMapping("/customer")
	public ResponseEntity<?> getBookingOfCustomer(Principal principal){
		String username=principal.getName();
		int customerId=getId.getIdByUsername(username);
		List<BookingDetailsDto> bookings=bookingService.getBookingOfCustomer(customerId);
		return ResponseEntity.ok(bookings);
		
	}
	
	@GetMapping("/customer/{bookingType}/{bookingPeriod}")
	public ResponseEntity<?> getBookingOfCustomer(Principal principal,@PathVariable String bookingType,@PathVariable String bookingPeriod){
		String username=principal.getName();
		int customerId=getId.getIdByUsername(username);
		List<BookingDetailsDto> bookings=bookingService.getMyBooking(customerId,bookingType,bookingPeriod);
		return ResponseEntity.ok(bookings);
		
	}
	
	
	@PutMapping("/cancel/{bookingId}")
	public ResponseEntity<?> cancelBooking(Principal principal,@PathVariable int bookingId) {
	    try {
	    	String username=principal.getName();
	    	int customerId=getId.getIdByUsername(username);
	        Booking booked = bookingService.cancelBooking(customerId, bookingId);
	        return ResponseEntity.ok(booked);
	    } catch (InvalidIdException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
		
	}
	
	@GetMapping("/all")
	public List<BookingDetailsDto> allBooking(Principal principal){
		
		String username=principal.getName();
    	int serviceProviderId=getId.getIdByUsername(username);
		return bookingService.allBooking(serviceProviderId);
	}
	
	
	
}
