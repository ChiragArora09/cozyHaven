package com.group_3.cozyHaven.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.dto.BookingTicket;
import com.group_3.cozyHaven.dto.FlightBetweenStopsDto;
import com.group_3.cozyHaven.dto.FlightInputDto;
import com.group_3.cozyHaven.dto.FlightPayment;
import com.group_3.cozyHaven.dto.MessageDto;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.City;
import com.group_3.cozyHaven.model.Flight;
import com.group_3.cozyHaven.model.FlightBooking;
import com.group_3.cozyHaven.model.FlightCity;
import com.group_3.cozyHaven.model.FlightClass;
import com.group_3.cozyHaven.model.FlightSeat;
import com.group_3.cozyHaven.model.FlightSeatBooking;
import com.group_3.cozyHaven.model.FlightTraveller;
import com.group_3.cozyHaven.service.FlightBookingService;
import com.group_3.cozyHaven.service.FlightCityService;
import com.group_3.cozyHaven.service.FlightClassService;
import com.group_3.cozyHaven.service.FlightSeatBookingService;
import com.group_3.cozyHaven.service.FlightSeatService;
import com.group_3.cozyHaven.service.FlightService;
import com.group_3.cozyHaven.service.FlightTravellerService;
import com.group_3.cozyHaven.service.CityService;
import com.group_3.cozyHaven.utility.GetId;

@RestController
@RequestMapping("/flight")
@CrossOrigin(origins = {"http://localhost:4200"})
public class FlightController {
	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private FlightCityService flightCityService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private FlightClassService flightClassService;
	
	@Autowired
	private FlightSeatService flightSeatService;
	
	@Autowired
	private FlightBookingService flightBookingService;
	
	@Autowired
	private FlightTravellerService flightTravellerService;
	
	@Autowired
	private FlightSeatBookingService flightSeatBookingService;
	
	@Autowired
	private GetId getId;
	
	
	// ADDING A FLIGHT BY SERVICE PROVIDER
	@PostMapping("/add-flight")
	public ResponseEntity<?> addFlight(Principal principal, @RequestBody Flight flight, MessageDto dto){
		try {
			String serviceProviderUserName = principal.getName();
			int serviceProviderId = getId.getIdByUsername(serviceProviderUserName);
			flight = flightService.addFlight(serviceProviderId, flight);
			return ResponseEntity.ok(flight);
		}catch (InputValidationException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);	
		}
	}
	
	// ADDING A CITY BY SERVICE PROVIDER
	@PostMapping("/add-city")
	public ResponseEntity<?> addCity(@RequestBody City city){
		city = cityService.addCity(city);
		return ResponseEntity.ok(city);
	}
	
	
	// ADDING FLIGHT AND ITS STOPS
	@PostMapping("/add/flight-city/{flightid}/{cityid}")
	public ResponseEntity<?> addBusAndItsStop(@PathVariable int flightid, @PathVariable int cityid, @RequestBody FlightCity flightCity, MessageDto dto){
		try {
			flightCity = flightCityService.addFlightAndCity(flightid, cityid, flightCity);
			return ResponseEntity.ok(flightCity); 
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto); 
		}
	}
	
	// ADDING FLIGHT CLASS
	@PostMapping("/add-flight-class/{fid}")
	public ResponseEntity<?> addFlightClass(@PathVariable int fid, @RequestBody FlightClass flightClass, MessageDto dto){
		try {
			flightClass = flightClassService.addFlightClass(fid, flightClass);
			return ResponseEntity.ok(flightClass); 
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto); 
		}
	}
	
	// ADDING SEATS TO A FLIGHT
	@PostMapping("/add-flight-seat/{cid}") // class id
	public ResponseEntity<?> addClassSeat(@PathVariable int cid, @RequestBody FlightSeat flightSeat, MessageDto dto){
		try {
			flightSeat = flightSeatService.addFlightSeat(cid, flightSeat);
			return ResponseEntity.ok(flightSeat); 
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto); 
		}
	}
	
	// GETTING FIGHT INFORMATION BETWEEN TWO CITIES
	@PostMapping("/flight-between-cities")
	public List<FlightBetweenStopsDto> flightBetweenStopsDtos(@RequestBody FlightInputDto flightInputDto){
		return flightService.getFlightsBetweenStops(flightInputDto.getSource(), flightInputDto.getDestination(), flightInputDto.getClassType());
	}
	
	// PROCESSING THE BOOKING
	@PostMapping("/booking/{flightId}")
	public ResponseEntity<?> addBooking(Principal principal, @PathVariable int flightId, @RequestBody FlightBooking flightBooking) throws InputValidationException{
		String CustomerUsername = principal.getName();
		int customerId = getId.getIdByUsername(CustomerUsername);
		flightBooking = flightBookingService.addBooking(customerId, flightId, flightBooking);
		return ResponseEntity.ok(flightBooking); 
	}
	
	// ADDING PASSENGERS
	@PostMapping("/booking/travellers/{bid}")
	public ResponseEntity<?> addPassengers(@PathVariable int bid, @RequestBody List<FlightTraveller> flightTraveller) throws InputValidationException {
		List<FlightTraveller> savedTravellersList = new ArrayList<>();
		
	    for (FlightTraveller traveller : flightTraveller) {
	        FlightTraveller savedTraveller = flightTravellerService.addTraveller(bid, traveller);
	        savedTravellersList.add(savedTraveller);
	    }
	    return ResponseEntity.ok(savedTravellersList);	
	}
	
	// BOOKING SEATS
	@PostMapping("/seat-booking/{bid}")
	public ResponseEntity<?> confirmSeatBooking(@PathVariable int bid, @RequestBody List<Integer> flightSeats) throws InputValidationException, InvalidIdException {
		List<FlightSeatBooking> flightSeatBookings = flightSeatBookingService.confirmSeatBooking(bid, flightSeats);
		return ResponseEntity.ok(flightSeatBookings);
	}
	
	// GETTING AVAILABLE SEATS
	@GetMapping("/booking/{bookingId}/{flightId}/get-seats")
	public List<?> getAvailableSeats(@PathVariable int flightId, @PathVariable int bookingId) throws InputValidationException {
		// the booking ID will come from the previous step as soon as the customer finishes the initial booking process
		return flightSeatService.getAvailableSeats(flightId, bookingId);
	}
	
	// PAYMENT INFO
	@GetMapping("/{bid}/payment")
	public List<FlightPayment> totalAmountCalculation(@PathVariable int bid) throws InputValidationException {
		List<FlightPayment> list = flightSeatBookingService.calculateTotalAmount(bid);
		return list;
	}

	// GETTING BOOKING TICKET
	@GetMapping("/booking-ticket/{bid}")
	public List<BookingTicket> getBookingReceipt(@PathVariable int bid) {
		return flightService.getBookingTicket(bid);
	}
		
}
