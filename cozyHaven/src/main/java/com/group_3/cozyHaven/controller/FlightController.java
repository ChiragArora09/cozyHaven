package com.group_3.cozyHaven.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.dto.FlightBetweenStopsDto;
import com.group_3.cozyHaven.dto.FlightInputDto;
import com.group_3.cozyHaven.dto.MessageDto;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Flight;
import com.group_3.cozyHaven.model.FlightBooking;
import com.group_3.cozyHaven.model.FlightClass;
import com.group_3.cozyHaven.model.FlightRoute;
import com.group_3.cozyHaven.model.FlightSeat;
import com.group_3.cozyHaven.model.Route;
import com.group_3.cozyHaven.service.FlightBookingService;
import com.group_3.cozyHaven.service.FlightClassService;
import com.group_3.cozyHaven.service.FlightRouteService;
import com.group_3.cozyHaven.service.FlightSeatBookingService;
import com.group_3.cozyHaven.service.FlightSeatService;
import com.group_3.cozyHaven.service.FlightService;
import com.group_3.cozyHaven.service.FlightTravellerService;
import com.group_3.cozyHaven.service.RouteService;

@RestController
@RequestMapping("/flight")
public class FlightController {
	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private FlightRouteService flightRouteService;
	
	@Autowired
	private RouteService routeService;
	
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
	
	@PostMapping("/add/{serviceProviderId}")
	public ResponseEntity<?> addFlight(@PathVariable int serviceProviderId, @RequestBody Flight flight, MessageDto dto){
		try {
			flight = flightService.addFlight(serviceProviderId, flight);
			return ResponseEntity.ok(flight);
		}catch (InputValidationException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);	
		}
	}
	
	@PostMapping("/route")
	public ResponseEntity<?> addRoute(@RequestBody Route route){
		route = routeService.addRoute(route);
		return ResponseEntity.ok(route);
	}
	
	@PostMapping("/add/flight-route/{fid}/{rid}")
	public ResponseEntity<?> addFlightRoute(@PathVariable int fid, @PathVariable int rid, @RequestBody FlightRoute flightRoute, MessageDto dto){
		try {
			flightRoute = flightRouteService.flightFollowsRoute(fid, rid, flightRoute);
			return ResponseEntity.ok(flightRoute); 
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto); 
		}
	}
	
	@PostMapping("/add/flight-class/{fid}")
	public ResponseEntity<?> addFlightClass(@PathVariable int fid, @RequestBody FlightClass flightClass, MessageDto dto){
		try {
			flightClass = flightClassService.addFlightClass(fid, flightClass);
			return ResponseEntity.ok(flightClass); 
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto); 
		}
	}
	
	@PostMapping("/add/flight-seat/{cid}")
	public ResponseEntity<?> addClassSeat(@PathVariable int cid, @RequestBody FlightSeat flightSeat, MessageDto dto){
		try {
			flightSeat = flightSeatService.addFlightSeat(cid, flightSeat);
			return ResponseEntity.ok(flightSeat); 
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto); 
		}
	}
	
	@GetMapping("/flight-between-station")
	public List<FlightBetweenStopsDto> flightBetweenStops(@RequestBody FlightInputDto flightInputDto){
		return flightService.getFlightBetweenStops(flightInputDto.getSource(), flightInputDto.getDestination(), flightInputDto.getClassType());
	}
	
	@PostMapping("/booking/{cid}/{fid}")
	public ResponseEntity<?> addBooking(@PathVariable int cid, @PathVariable int fid, @RequestBody FlightBooking flightBooking, MessageDto dto) throws InputValidationException{
			flightBooking = flightBookingService.addBooking(cid, fid, flightBooking);
			return ResponseEntity.ok(flightBooking); 
	}
	
}
