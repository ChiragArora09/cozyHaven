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
		List<Object[]> list = busRepository.getBusIdsForSourceAndDestination(source, destination);
		List<Integer> busIds = new ArrayList<>();
		for(Object[] ids: list) {
			int id = (int) ids[0];
			busIds.add(id);
		}
		
		// sourceInfo [] and destinationInfo []
		// bs.arrival, bs.departure, bs.distance, b.name, b.number, b.type, b.description, s.stopName, b.id
		
		System.out.println(busIds);
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
			BusBetweenStopsDto betweenStopsDto = new BusBetweenStopsDto(sourceInfo[3].toString(), sourceInfo[4].toString(), (BusType)sourceInfo[5], sourceInfo[6].toString(), sourceInfo[7].toString(), destinationInfo[7].toString(), (LocalTime) sourceInfo[0], (LocalTime) destinationInfo[0], distance, totalAmount, (int)sourceInfo[8]);
			busBetweenStopsDtos.add(betweenStopsDto);
		}
		return busBetweenStopsDtos;
		
	}
	

}
