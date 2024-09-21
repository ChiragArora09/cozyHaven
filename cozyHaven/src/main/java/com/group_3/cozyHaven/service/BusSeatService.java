package com.group_3.cozyHaven.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.dto.BusAvailableSeatsDto;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Bus;
import com.group_3.cozyHaven.model.BusBooking;
import com.group_3.cozyHaven.model.BusSeat;
import com.group_3.cozyHaven.repository.BusSeatRepository;

@Service
public class BusSeatService {
	
	@Autowired
	private BusService busService;
	
	@Autowired
	private BusSeatRepository busSeatRepository;
	
	@Autowired
	private BusBookingService busBookingService;

	public BusSeat addBusSeat(int busId, BusSeat busSeat) throws InvalidIdException {
		Bus bus = busService.findById(busId);
		busSeat.setBus(bus);
		return busSeatRepository.save(busSeat);
	}
	
 	public BusSeat getById(int seatId) throws InvalidIdException {
		Optional<BusSeat> optional = busSeatRepository.findById(seatId);
		if(optional.isEmpty())
			throw new InvalidIdException("invalid bus seat Id");
		
		return optional.get();
 	}

	public List<?> getAvailableSeats(int busId, int bookingId) throws InvalidIdException, InputValidationException {
		
		BusBooking busBooking = busBookingService.getById(bookingId);
		
		int sourceStopNumber = busBooking.getSourceStopNumber();
		int destinationStopNumber = busBooking.getDestinationStopNumber();
		LocalDate bookingDate = busBooking.getDate();
		
		List<Object[]> list = busSeatRepository.getAvailableSeats(busId, sourceStopNumber, destinationStopNumber, bookingDate);
		
		List<BusAvailableSeatsDto> busSeat = new ArrayList<>();
		
		for(Object[] obj : list) {
			int seatId = (int) obj[0];
			String seatNumber = obj[1].toString();
			String seatType = obj[2].toString();
			
			BusAvailableSeatsDto dto = new BusAvailableSeatsDto(seatId, seatNumber, seatType);
			busSeat.add(dto);
		}
		return busSeat;
		
	}
	

}
