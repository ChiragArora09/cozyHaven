package com.group_3.cozyHaven.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Route;
import com.group_3.cozyHaven.repository.RouteRepository;

@Service
public class RouteService {
	
	@Autowired
	private RouteRepository routeRepository;
	
	public Route addRoute(Route route) {
		return routeRepository.save(route);
	}
	
	public Route findById(int rid) throws InvalidIdException {
		Optional<Route> optional = routeRepository.findById(rid);
		if(optional.isEmpty())
			throw new InvalidIdException("Flight Id invalid");
		return optional.get();
	}
}
