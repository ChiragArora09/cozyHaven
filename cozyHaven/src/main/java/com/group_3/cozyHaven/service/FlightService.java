package com.group_3.cozyHaven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.Flight;
import com.group_3.cozyHaven.model.ServiceProvider;
import com.group_3.cozyHaven.repository.FlightRepository;

@Service
public class FlightService {
	
	@Autowired
	private ServiceProviderService serviceProviderService;
	
	@Autowired
	private FlightRepository flightRepository;
	
	public Flight addFlight(int serviceProviderId, Flight flight) throws InputValidationException {
		ServiceProvider serviceProvider = serviceProviderService.getById(serviceProviderId);
		flight.setServiceProvider(serviceProvider);
		return flightRepository.save(flight);
	}

}
