package com.group_3.cozyHaven.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
