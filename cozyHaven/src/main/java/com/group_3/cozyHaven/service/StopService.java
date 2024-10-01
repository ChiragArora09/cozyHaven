package com.group_3.cozyHaven.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Stop;
import com.group_3.cozyHaven.repository.StopRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class StopService {
	
	@Autowired 
	private StopRepository stopRepository;
	
	private Logger logger = LoggerFactory.getLogger(StopService.class);
	
	

	public Stop addStop(Stop stop) {
		logger.info("Stop " + stop.getStopName() + " added by a service provider");
		return stopRepository.save(stop);
	}
	
	public Stop findById(int sid) throws InvalidIdException {
		Optional<Stop> optional = stopRepository.findById(sid);
		if(optional.isEmpty())
			throw new InvalidIdException("Flight Id invalid");
		return optional.get();
	}

}
