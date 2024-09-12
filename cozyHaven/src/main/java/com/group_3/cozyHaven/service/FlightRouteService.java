package com.group_3.cozyHaven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Flight;
import com.group_3.cozyHaven.model.FlightRoute;
import com.group_3.cozyHaven.model.Route;
import com.group_3.cozyHaven.repository.FlightRouteRepository;

@Service
public class FlightRouteService {
	
	@Autowired
	private FlightRouteRepository flightRouteRepository;
	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private RouteService routeService;
	
	public FlightRoute flightFollowsRoute(int fid, int rid, FlightRoute flightRoute) throws InvalidIdException {
		
		Flight flight = flightService.findById(fid);
		Route route = routeService.findById(rid);
		
		flightRoute.setFlight(flight);
		flightRoute.setRoute(route);
		
		return flightRouteRepository.save(flightRoute);
	}
}
