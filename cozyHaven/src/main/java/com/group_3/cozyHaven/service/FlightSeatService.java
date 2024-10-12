package com.group_3.cozyHaven.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.dto.FlightAvailableSeatDto;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.FlightBooking;
import com.group_3.cozyHaven.model.FlightClass;
import com.group_3.cozyHaven.model.FlightSeat;
import com.group_3.cozyHaven.repository.FlightSeatRepository;

@Service
public class FlightSeatService {
	
	@Autowired
	private FlightSeatRepository flightSeatRepository;
	
	@Autowired
	private FlightClassService flightClassService;
	
	@Autowired
	private FlightBookingService flightBookingService;
	
	public List<FlightSeat> addFlightSeat(int classId, List<FlightSeat> flightSeats) throws InvalidIdException {
		FlightClass flightClass =  flightClassService.findById(classId); // finding flight by flightId
		List<FlightSeat> flightSeatList = new ArrayList<>();
		
		for(FlightSeat flightSeat : flightSeats) {
			flightSeat.setFlightClass(flightClass);
			flightSeatList.add(flightSeat);
		}
		flightSeatRepository.saveAll(flightSeatList);
		return flightSeatList;
	}


	public FlightSeat getById(int seatId) throws InvalidIdException {
			Optional<FlightSeat> optional = flightSeatRepository.findById(seatId);
			if(optional.isEmpty())
				throw new InvalidIdException("Flight Id invalid");
			
			return optional.get();
	}


	public List<?> getAvailableSeats(int flightId, int bookingId, LocalDate date) throws InputValidationException {
		FlightBooking flightBooking = flightBookingService.getById(bookingId);
		
		int sourceCityNumber = flightBooking.getSourceCityNumber();
		int destinationCityNumber = flightBooking.getDestinationCityNumber();
//		LocalDate bookingDate = flightBooking.getDate();
		
		List<Object[]> list = flightSeatRepository.getAvailableSeats(flightId, sourceCityNumber, destinationCityNumber, date);
		
		List<FlightAvailableSeatDto> flightSeatsList = new ArrayList<>();
		
		for(Object[] obj : list) {
			int seatId = (int) obj[0];
			String seatNumber = obj[1].toString();
			String seatType = obj[2].toString();
			String classType = obj[3].toString();
			
			FlightAvailableSeatDto dto = new FlightAvailableSeatDto(seatId, seatNumber, seatType, classType);
			flightSeatsList.add(dto);
		}
		return flightSeatsList;
	}


}
