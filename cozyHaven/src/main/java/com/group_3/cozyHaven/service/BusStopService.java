package com.group_3.cozyHaven.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Bus;
import com.group_3.cozyHaven.model.BusStop;
import com.group_3.cozyHaven.model.Stop;
import com.group_3.cozyHaven.repository.BusStopRepository;

@Service
public class BusStopService {
	
	private Logger logger = LoggerFactory.getLogger(BusStopService.class);
	
	@Autowired
	private BusService busService;
	
	@Autowired
	private StopService stopService;
	
	@Autowired
	private BusStopRepository busStopRepository;

	public BusStop addBusAndStop(int busid, int stopid, BusStop busStop) throws InvalidIdException {
		Bus bus = busService.findById(busid);
		Stop stop = stopService.findById(stopid);
		
		busStop.setBus(bus);
		busStop.setStop(stop);
		
		logger.info("Bus " + bus.getName() + " " + bus.getNumber() + " " + "now has a stop " + stop.getStopName());
		
		return busStopRepository.save(busStop);
		
	}

}
