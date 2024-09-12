package com.group_3.cozyHaven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.FlightClass;
import com.group_3.cozyHaven.model.FlightSeat;
import com.group_3.cozyHaven.repository.FlightSeatRepository;

@Service
public class FlightSeatService {
	
	@Autowired
	private FlightSeatRepository flightSeatRepository;
	
	@Autowired
	private FlightClassService flightClassService;
	
	public FlightSeat addFlightSeat(int classId, FlightSeat flightSeat) throws InvalidIdException {
		FlightClass flightClass =  flightClassService.findById(classId); // finding flight by flightId
		flightSeat.setFlightClass(flightClass); // inserting id into flightClass
		return flightSeatRepository.save(flightSeat);
	}

}
