package com.group_3.cozyHaven.service;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.dto.BookingTicket;
import com.group_3.cozyHaven.dto.BusBetweenStopsDto;
import com.group_3.cozyHaven.enums.BusType;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Bus;
import com.group_3.cozyHaven.model.ServiceProvider;
import com.group_3.cozyHaven.repository.BusRepository;

@Service
public class BusService {
	private Logger logger = LoggerFactory.getLogger(BusService.class);
	
	@Autowired
	private ServiceProviderService serviceProviderService;
	
	@Autowired
	private BusRepository busRepository;

	public Bus addBus(int serviceProviderId, Bus bus) throws InputValidationException {
		ServiceProvider serviceProvider = serviceProviderService.getById(serviceProviderId);
		bus.setServiceProvider(serviceProvider);
		logger.info("A new bus added by service provider "+ serviceProvider.getFullName()+" id:"+serviceProviderId);
		return busRepository.save(bus);
	}
	
	public Bus findById(int bid) throws InvalidIdException {
		Optional<Bus> optional = busRepository.findById(bid);
		if(optional.isEmpty()) {
			logger.error("Invalid Bus id given");
			throw new InvalidIdException("Bus Id invalid");
		}
		return optional.get();
	}

	public List<BusBetweenStopsDto> getBusBetweenStops(String source, String destination) {
		List<Object[]> list = busRepository.getBusIdsForSourceAndDestination(source, destination);
		List<Integer> busIds = new ArrayList<>();
		logger.info("Fetching bus IDs of buses that travel between "+ source + " and " + destination);
		for(Object[] ids: list) {
			int id = (int) ids[0];
			busIds.add(id);
		}
		
		// sourceInfo [] and destinationInfo []
		// bs.arrival, bs.departure, bs.distance, b.name, b.number, b.type, b.description, s.stopName, b.id
		
		List<BusBetweenStopsDto> busBetweenStopsDtos = new ArrayList<>();
		for (int i=0;i<busIds.size();i++) {
			List<Object[]> busInfo = busRepository.getBusBetweenStops(busIds.get(i), source, destination);
			
			Object[] sourceInfo = busInfo.get(0);
			Object[] destinationInfo = busInfo.get(1);
			
			int PricePerKm = 0;
			int distance = (int) destinationInfo[2] - (int) sourceInfo[2];
			if(sourceInfo[5].toString().equals("AC")) {
				PricePerKm = distance * 8; // 8 Rs. per kilometer
			} else {
				PricePerKm = distance * 3; // 3 Rs. per kilometer
			}
			double baseFare = 100;
			
			double totalAmount = baseFare+PricePerKm;
			// (String busName, String busNumber, BusType busType, String busDescription, String source, String destination, LocalTime sourceArrival, LocalTime destinationArrival, int distance, double amount)
			BusBetweenStopsDto betweenStopsDto = new BusBetweenStopsDto(sourceInfo[3].toString(), sourceInfo[4].toString(), (BusType)sourceInfo[5], sourceInfo[6].toString(), sourceInfo[7].toString(), destinationInfo[7].toString(), (LocalTime) sourceInfo[0], (LocalTime) destinationInfo[0], distance, totalAmount, (int)sourceInfo[8], (int)sourceInfo[9], (int)destinationInfo[9]);
			busBetweenStopsDtos.add(betweenStopsDto);
		}
		
        List<BusBetweenStopsDto> filteredBusesBySource = busBetweenStopsDtos.stream()
                .filter(bus -> bus.getSource().equals(source))
                .collect(Collectors.toList());
        logger.info("Buses fetched for "+ source + " and " + destination);
		return filteredBusesBySource;
		
	}

	public List<BookingTicket> getBookingTicket(int bid) {
		List<Object[]> list = busRepository.getBookingTicket(bid);
		List<BookingTicket> listDto = new ArrayList<>();
		
		logger.info("Generating booking ticket for booking id: "+bid);
		
		for(Object[] obj : list) {
			String busName = obj[0].toString();
			String busNumber = obj[1].toString();
			String busType = obj[2].toString();
			String passengerName = obj[3].toString();
			int passengerAge = (int)obj[4];
			String seatNumber = obj[5].toString();
			String seatType = obj[6].toString();
			String date = obj[7].toString();
			String source = obj[8].toString();
			String destination = obj[9].toString();
			String status = obj[10].toString();
			
			BookingTicket bookingTicket = new BookingTicket(date, source, destination, status, seatNumber, busType, busName, passengerName, passengerAge, busNumber, seatType);
			listDto.add(bookingTicket);
		}
		return listDto;
	}

	
}
