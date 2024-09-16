package com.group_3.cozyHaven.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.dto.BusBetweenStopsDto;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Bus;
import com.group_3.cozyHaven.model.BusSeat;
import com.group_3.cozyHaven.repository.BusSeatRepository;

@Service
public class BusSeatService {
	
	@Autowired
	private BusService busService;
	
	@Autowired
	private BusSeatRepository busSeatRepository;

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

	public List<BusBetweenStopsDto> getBusBetweenStops(String source, String destination) {
		// TODO Auto-generated method stub
		return null;
	} 

}
