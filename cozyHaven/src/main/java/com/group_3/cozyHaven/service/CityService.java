package com.group_3.cozyHaven.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.City;
import com.group_3.cozyHaven.repository.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	public City findById(int cityId) throws InvalidIdException {
		Optional<City> optional = cityRepository.findById(cityId);
		if(optional.isEmpty())
			throw new InvalidIdException("Id invalid");
		return optional.get();
	}

	public City addCity(City city) {
		return cityRepository.save(city);
	}
}
