package com.group_3.cozyHaven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.exception.RoomUnavailableException;
import com.group_3.cozyHaven.model.Booking;
import com.group_3.cozyHaven.model.ServiceProvider;
import com.group_3.cozyHaven.repository.RoomRepository;
import com.group_3.cozyHaven.service.BookingService;


@RestController
@RequestMapping("/booking")
public class BookingController {
	
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/room/{roomId}/{customerId}")
	public ResponseEntity<?> reviewBooking(@PathVariable int roomId,@PathVariable int customerId,@RequestBody Booking booking) {
		
		try {
			Booking book=bookingService.bookRoom(roomId,customerId,booking);
			return ResponseEntity.ok(book);
		} catch (InvalidIdException | RoomUnavailableException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} 
	}
}
