package com.group_3.cozyHaven.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.dto.BookingTicket;
import com.group_3.cozyHaven.dto.FlightBetweenStopsDto;
import com.group_3.cozyHaven.enums.ClassType;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
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
		ServiceProvider serviceProvider = serviceProviderService.getById(serviceProviderId); // finding service provider by serviceProviderId
		flight.setServiceProvider(serviceProvider);
		return flightRepository.save(flight);
	}

	public Flight findById(int fid) throws InvalidIdException {
		Optional<Flight> optional = flightRepository.findById(fid);
		if(optional.isEmpty())
			throw new InvalidIdException("Flight Id invalid");
		
		return optional.get();
	}

	public List<FlightBetweenStopsDto> getFlightBetweenStops(String source, String destination, ClassType classType) {
		
		List<Object[]> list = flightRepository.getFlightBetweenStops(source, destination, classType);
 		List<FlightBetweenStopsDto> listDto = new ArrayList<>();
		for(Object[] obj : list) {
			String name = obj[0].toString();
			String number = obj[1].toString();
			String sourceString = obj[2].toString();
			String destinationString = obj[3].toString();
			LocalTime sourceDeparture = (LocalTime) obj[4];
			LocalTime destinationArrival = (LocalTime) obj[5];
			double amount = (double) obj[6];
			FlightBetweenStopsDto dto = new FlightBetweenStopsDto(name, number, sourceString, destinationString, sourceDeparture, destinationArrival, amount);
			listDto.add(dto);
		}
		return listDto;
	}

	public List<BookingTicket> getBookingTicket(int bid) {
		List<Object[]> list = flightRepository.getBookingTicket(bid);
		List<BookingTicket> listDto = new ArrayList<>();
		for(Object[] obj : list) {
			String date = obj[0].toString();
			String source = obj[1].toString();
			String destination = obj[2].toString();
			String status = obj[3].toString();
			String seatNumber = obj[4].toString();
			String type = obj[5].toString();
			String flightName = obj[6].toString();
			String travellerName = obj[7].toString();
			int age = (int) obj[8];
			BookingTicket bookingTicket = new BookingTicket(date, source, destination, status, seatNumber, type, flightName, travellerName, age);
			listDto.add(bookingTicket);
		}
		return listDto;
	}
	
	
}
