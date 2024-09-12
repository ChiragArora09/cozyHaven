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
import com.group_3.cozyHaven.model.Flight;
import com.group_3.cozyHaven.model.FlightRoute;
import com.group_3.cozyHaven.model.Route;
import com.group_3.cozyHaven.service.FlightRouteService;
import com.group_3.cozyHaven.service.FlightService;
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

}
