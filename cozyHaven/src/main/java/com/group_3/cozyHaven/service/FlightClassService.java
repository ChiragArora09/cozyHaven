package com.group_3.cozyHaven.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Flight;
import com.group_3.cozyHaven.model.FlightClass;
import com.group_3.cozyHaven.repository.FlightClassRepository;

@Service
public class FlightClassService {
	
	@Autowired
	private FlightClassRepository flightClassRepository;
	
	@Autowired
	private FlightService flightService;
	
	public FlightClass addFlightClass(int flightId, FlightClass flightClass) throws InvalidIdException {
		Flight flight =  flightService.findById(flightId); // finding flight by flightId
		flightClass.setFlight(flight); // inserting id into flightClass
		return flightClassRepository.save(flightClass);
	}
	
	public FlightClass findById(int fcid) throws InvalidIdException {
		Optional<FlightClass> optional = flightClassRepository.findById(fcid);
		if(optional.isEmpty())
			throw new InvalidIdException("Flight Id invalid");
		
		return optional.get();
	}
}
