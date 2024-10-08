package com.group_3.cozyHaven.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.dto.BookingTicket;
import com.group_3.cozyHaven.dto.BusBetweenStopsDto;
import com.group_3.cozyHaven.dto.BusInputDto;
import com.group_3.cozyHaven.dto.MessageDto;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Bus;
import com.group_3.cozyHaven.model.BusBooking;
import com.group_3.cozyHaven.model.BusPassenger;
import com.group_3.cozyHaven.model.BusSeat;
import com.group_3.cozyHaven.model.BusSeatBooking;
import com.group_3.cozyHaven.model.BusStop;
import com.group_3.cozyHaven.model.Payment;
import com.group_3.cozyHaven.model.Stop;
import com.group_3.cozyHaven.service.BusBookingService;
import com.group_3.cozyHaven.service.BusPassengerService;
import com.group_3.cozyHaven.service.BusSeatBookingService;
import com.group_3.cozyHaven.service.BusSeatService;
import com.group_3.cozyHaven.service.BusService;
import com.group_3.cozyHaven.service.BusStopService;
import com.group_3.cozyHaven.service.StopService;
import com.group_3.cozyHaven.utility.GetId;

@RestController
@RequestMapping("/bus")
public class BusController {
	
	@Autowired
	private BusService busService;
	
	@Autowired
	private StopService stopService;
	
	@Autowired 
	private BusStopService busStopService;
	
	@Autowired
	private BusSeatService busSeatService;
	
	@Autowired
	private BusBookingService busBookingService;
	
	@Autowired
	private BusPassengerService busPassengerService;
	
	@Autowired
	private BusSeatBookingService busSeatBookingService;
	
	@Autowired
	private GetId getId;
	
	// ADDING A BUS BY SERVICE PROVIDER 
	@PostMapping("/add-bus")
	public ResponseEntity<?> addBus(Principal principal, @RequestBody Bus bus, MessageDto dto){
		try {
			String serviceProviderUserName = principal.getName();
			int serviceProviderId = getId.getIdByUsername(serviceProviderUserName);
			bus = busService.addBus(serviceProviderId, bus);
			return ResponseEntity.ok(bus);
		}catch (InputValidationException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);	
		}
	}
	
	// ADDING A STOP BY SERVICE PROVIDER
	@PostMapping("/stop")
	public ResponseEntity<?> addStop(@RequestBody Stop stop){
		stop = stopService.addStop(stop);
		return ResponseEntity.ok(stop);
	}
	
	// ADDING BUS AND ITS STOPS
	@PostMapping("/add/bus-stop/{busid}/{stopid}")
	public ResponseEntity<?> addBusAndItsStop(@PathVariable int busid, @PathVariable int stopid, @RequestBody BusStop busStop, MessageDto dto){
		try {
			busStop = busStopService.addBusAndStop(busid, stopid, busStop);
			return ResponseEntity.ok(busStop); 
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto); 
		}
	}
	
	// ADDING SEAT TO A BUS
	@PostMapping("/add/bus-seat/{busId}")
	public ResponseEntity<?> addBusSeat(@PathVariable int busId, @RequestBody BusSeat busSeat, MessageDto dto){
		try {
			busSeat = busSeatService.addBusSeat(busId, busSeat);
			return ResponseEntity.ok(busSeat);
		}catch(InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	// GETTING BUS INFORMATION BETWEEN TWO STOPS
	@GetMapping("/bus-between-stops")
	public List<BusBetweenStopsDto> busBetweenStopsDtos(@RequestBody BusInputDto busInputDto){
		return busService.getBusBetweenStops(busInputDto.getSource(), busInputDto.getDestination());
	}
	
	// Processing the BOOKING
	@PostMapping("/booking/{busId}")
	public ResponseEntity<?> addBooking(Principal principal, @PathVariable int busId, @RequestBody BusBooking busBooking) throws InputValidationException{
		String CustomerUsername = principal.getName();
		int customerId = getId.getIdByUsername(CustomerUsername);
		busBooking = busBookingService.addBooking(customerId, busId, busBooking);
		return ResponseEntity.ok(busBooking); 
	}
	
	
	// ADDING PASSENGERS
	@PostMapping("/booking/passengers/{bid}")
	public ResponseEntity<?> addPassengers(@PathVariable int bid, @RequestBody List<BusPassenger> busPassengers) throws InputValidationException {
		List<BusPassenger> savedPassengers = new ArrayList<>();
		
	    for (BusPassenger passenger : busPassengers) {
	        BusPassenger savedPassenger = busPassengerService.addPassenger(bid, passenger);
	        savedPassengers.add(savedPassenger);
	    }
	    return ResponseEntity.ok(savedPassengers);	
	}
	
	// BOOKING SEATS
	@PostMapping("/seat-booking/{bid}")
	public ResponseEntity<?> confirmSeatBooking(@PathVariable int bid, @RequestBody List<Integer> busSeats) throws InputValidationException, InvalidIdException {
		List<BusSeatBooking> busSeatBookings = busSeatBookingService.confirmSeatBooking(bid, busSeats);
		return ResponseEntity.ok(busSeatBookings);
	}
	
	// GETTING AVAILABLE SEATS
	@GetMapping("/booking/{bookingId}/{busId}/get-seats")
	// the bookingId is the booking id of the booking of customer who has started the initial process of the booking
	public List<?> getAvailableSeats(@PathVariable int busId, @PathVariable int bookingId) throws InvalidIdException, InputValidationException {
		return busSeatService.getAvailableSeats(busId, bookingId);
	}
	
	// PAYMENT INFO
	@GetMapping("/{bid}/payment")
	public List<Payment> totalAmountCalculation(@PathVariable int bid) throws InputValidationException {
		List<Payment> list = busSeatBookingService.calculateTotalAmount(bid);
		return list;
	}
	
	// GETTING BOOKING TICKET
	@GetMapping("/booking-ticket/{bid}")
	public List<BookingTicket> getBookingReceipt(@PathVariable int bid) {
		return busService.getBookingTicket(bid);
	}
	
}
