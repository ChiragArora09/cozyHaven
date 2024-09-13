package com.group_3.cozyHaven.service;

import java.util.ArrayList;
import java.util.List;

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

	public List<?> getAvailableSeats(int fid, String date) {
		List<Object[]> list = flightSeatRepository.getAvailableSeats(fid, date);
		List<String> listDto = new ArrayList<>();
		for(Object[] obj : list) {
			String seat = obj[0].toString();
			listDto.add(seat);
		}
		return listDto;
	}

}
