package com.group_3.cozyHaven.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.dto.BookingTicket;
import com.group_3.cozyHaven.dto.FlightBetweenStopsDto;
import com.group_3.cozyHaven.dto.FlightCityInputDto;
import com.group_3.cozyHaven.dto.FlightClassesAndSeatsDto;
import com.group_3.cozyHaven.dto.FlightInputDto;
import com.group_3.cozyHaven.dto.FlightPayment;
import com.group_3.cozyHaven.dto.MakePaymentDto;
import com.group_3.cozyHaven.dto.MessageDto;
import com.group_3.cozyHaven.dto.ReviewOnFlight;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.City;
import com.group_3.cozyHaven.model.Flight;
import com.group_3.cozyHaven.model.FlightBooking;
import com.group_3.cozyHaven.model.FlightCity;
import com.group_3.cozyHaven.model.FlightClass;
import com.group_3.cozyHaven.model.FlightOffer;
import com.group_3.cozyHaven.model.FlightSeat;
import com.group_3.cozyHaven.model.FlightSeatBooking;
import com.group_3.cozyHaven.model.FlightTraveller;
import com.group_3.cozyHaven.service.FlightBookingService;
import com.group_3.cozyHaven.service.FlightCityService;
import com.group_3.cozyHaven.service.FlightClassService;
import com.group_3.cozyHaven.service.FlightOfferService;
import com.group_3.cozyHaven.service.FlightReviewService;
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
	private FlightReviewService flightReviewService;

	@Autowired
	private FlightOfferService flightOfferService;
	
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
	
	// GET MY FLIGHTS
	@GetMapping("/get-my-flights")
	public List<Flight> getFlights(Principal principal) throws InputValidationException{
		String serviceProviderUsername = principal.getName();
		int serviceProviderId = getId.getIdByUsername(serviceProviderUsername);
		return flightService.getFlights(serviceProviderId);
	}
	
	// ADDING A CITY BY SERVICE PROVIDER
	@PostMapping("/add-city")
	public ResponseEntity<?> addCity(@RequestBody City city){
		city = cityService.addCity(city);
		return ResponseEntity.ok(city);
	}
	
	// GET ALL CITIES IN THE DATABASE
	@GetMapping("/get-flight-cities")
	public List<City> getFlightCitites(){
		return cityService.getAll();
	}
	
	@GetMapping("/get-cities")
	public List<FlightCity> getCities() {
		return flightService.getCities();
	}
	
	// ADDING FLIGHT AND ITS STOPS
	@PostMapping("/add/flight-city/{flightid}")
	public ResponseEntity<?> addFlightAndItsStop(@PathVariable int flightid, @RequestBody List<FlightCityInputDto> flightCityInputDto, MessageDto dto){
		try {
			System.out.println(flightid);
			return ResponseEntity.ok(flightCityService.addFlightAndCity(flightid, flightCityInputDto));
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto); 
		}
	}
	
	// UPDATING FLIGHT AND ITS STOPS
	@PostMapping("/update/flight-city/{flightid}")
	public void updateFlightAndItsStop(@PathVariable int flightid, @RequestBody List<FlightCityInputDto> flightCityInputDto, MessageDto dto){
		try {
			System.out.println(flightid);
			flightCityService.updateFlightAndCity(flightid, flightCityInputDto);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			System.out.println(dto.getMsg()); 
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
	public ResponseEntity<?> addClassSeat(@PathVariable int cid, @RequestBody List<FlightSeat> flightSeats, MessageDto dto){
		try {
			return ResponseEntity.ok(flightSeatService.addFlightSeat(cid, flightSeats)); 
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
	@PostMapping("/booking")
	public ResponseEntity<?> addBooking(Principal principal, @RequestBody FlightBooking flightBooking) throws InputValidationException{
		String CustomerUsername = principal.getName();
		int customerId = getId.getIdByUsername(CustomerUsername);
		flightBooking = flightBookingService.addBooking(customerId, flightBooking);
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
	@PostMapping("/booking/{bookingId}/{flightId}/get-seats")
	public List<?> getAvailableSeats(@PathVariable int flightId, @PathVariable int bookingId, @RequestBody LocalDate date) throws InputValidationException {
		// the booking ID will come from the previous step as soon as the customer finishes the initial booking process
		return flightSeatService.getAvailableSeats(flightId, bookingId, date);
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
	
	// GET OFFERS FOR A PARTICULAR BOOKING
	@GetMapping("/get-offers/{bid}")
	public List<?> getBookingOffers(@PathVariable int bid) {
		return flightService.getBookingOffers(bid);
	}
	
	// MAKE PAYMENT
	@PostMapping("/{bid}/confirm-booking")
	public void makePaymentAndConfirm(@PathVariable int bid, @RequestBody MakePaymentDto dto) {
		flightBookingService.makePayment(bid, dto);
	}
	
	// GET NUMBER OF LOYALTY POINTS TO APPLY IN FLIGHT BOOKING
	@GetMapping("{bid}/loyalty-points")
	public long getLoyaltyPoints(Principal principal, @PathVariable int bid) {
		String CustomerUsername = principal.getName();
		int customerId = getId.getIdByUsername(CustomerUsername);
		long points = flightSeatBookingService.getLoyaltyPoints(bid, customerId);
		return points;
	}
	
	// GET ALL OFFERS FOR A PARTICULAR FLIGHT ON CUSTOMER UI
	@GetMapping("/getAllOffers/{flightId}")
	public List<?> getOffers(@PathVariable int flightId) {
		return flightBookingService.getAllOffers(flightId);
	}
	
	// GET ALL OFFERS FOR A PARTICULAR FLIGHT SERVICE PROVIDER
	@GetMapping("/getMyFlightOffers/{flightId}")
	public List<?> getMyFlightOffers(@PathVariable int flightId) {
		return flightBookingService.getMyFlightOffers(flightId);
	}
	
	@PutMapping("/changeOfferStatus/{offerId}")
	public FlightOffer changeOfferStatus(@PathVariable int offerId) {
		return flightOfferService.changeStatus(offerId);
	}
	
	// GET ROUTE OF PARTICULAR FLIGHT BY FLIGHT ID
	@GetMapping("/getRoute/{flightId}")
	public List<FlightCity> getFlightRoutes(@PathVariable int flightId) {
		return flightService.getFlightRoute(flightId);
	}
		
	// GET FLIGHT CLASSES AND SEATS BY FLIGHT ID
	@GetMapping("/classesAndFlights/{flightId}")
	public List<FlightClassesAndSeatsDto> getClassesAndSeats(@PathVariable int flightId) {
		return flightClassService.getClassesAndSeats(flightId);
	}
	
	// GET REVIEWS ON MY FLIGHTS
	@GetMapping("/reviews-on-flight/{flightId}")
	public List<ReviewOnFlight> getReviewsOnParticularFlight(@PathVariable int flightId) {
		return flightReviewService.getReviewsOnParticularFlight(flightId);
	}
	
	// CREATE AN OFFER FOR A FLIGHT
	@PostMapping("/create-offer/{flightId}")
	public FlightOffer createOfferForFlight(@PathVariable int flightId, @RequestBody FlightOffer flightOffer) throws InvalidIdException {
		return flightOfferService.createOffer(flightId, flightOffer);
	}
	
	// GET OFFER DETAILS BY ID
	@GetMapping("offer-details/{offerId}")
	public FlightOffer getOfferDetails(@PathVariable int offerId) {
		return flightOfferService.getOfferDetails(offerId);
	}
	
	// EDIT AN OFFER
	@PutMapping("/offer-edit/{offerId}")
	public FlightOffer editOffer(@PathVariable int offerId, @RequestBody FlightOffer flightOffer) {
		return flightOfferService.editOffer(offerId, flightOffer);
	}
	
	// CHANGE FLIGHT STATUS
	@PutMapping("/flight-status/{flightId}")
	public void changeFlightStatus(@PathVariable int flightId) {
		flightService.changeStatus(flightId);
	}
	
}
