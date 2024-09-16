package com.group_3.cozyHaven.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.dto.BusBetweenStopsDto;
import com.group_3.cozyHaven.enums.BusType;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Bus;
import com.group_3.cozyHaven.model.ServiceProvider;
import com.group_3.cozyHaven.repository.BusRepository;

@Service
public class BusService {
	
	@Autowired
	private ServiceProviderService serviceProviderService;
	
	@Autowired
	private BusRepository busRepository;

	public Bus addBus(int serviceProviderId, Bus bus) throws InputValidationException {
		ServiceProvider serviceProvider = serviceProviderService.getById(serviceProviderId);
		bus.setServiceProvider(serviceProvider);
		return busRepository.save(bus);
	}
	
	public Bus findById(int bid) throws InvalidIdException {
		Optional<Bus> optional = busRepository.findById(bid);
		if(optional.isEmpty())
			throw new InvalidIdException("Bus Id invalid");
		
		return optional.get();
	}

	public List<BusBetweenStopsDto> getBusBetweenStops(String source, String destination) {
		List<Object[]> list = busRepository.getBusBetweenStops(source, destination);
		List<BusBetweenStopsDto> listDto = new ArrayList<>();
		for(Object[] obj : list) {
			String busName = obj[0].toString();
			String busNumber = obj[1].toString();
			BusType busType = (BusType) obj[2];
			String busDescription = obj[3].toString();
			String bsource = obj[4].toString();
			String bdestination = obj[5].toString();
			LocalTime sourceTime = (LocalTime) obj[6];
			LocalTime destinationTime = (LocalTime) obj[7];
			int distance = (int) obj[8];
			double amount = 100;
			
			BusBetweenStopsDto betweenStopsDto = new BusBetweenStopsDto(busName, busNumber, busType, busDescription, bsource, bdestination, sourceTime, destinationTime, distance, amount);
			// (String busName, String busNumber, BusType busType, String busDescription, String source, String destination, LocalTime sourceArrival, LocalTime destinationArrival, int distance, double amount)
			listDto.add(betweenStopsDto);
		}
		return listDto;
	}
	

}
