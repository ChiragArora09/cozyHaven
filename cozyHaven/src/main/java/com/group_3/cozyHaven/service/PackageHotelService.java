package com.group_3.cozyHaven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.model.PackageHotel;
import com.group_3.cozyHaven.repository.PackageHotelRepository;

@Service
public class PackageHotelService {

	@Autowired
	private PackageHotelRepository packageHotelRepository;
	
	public PackageHotel addHotel(PackageHotel packageHotel) {
		return packageHotelRepository.save(packageHotel);
	}

}
