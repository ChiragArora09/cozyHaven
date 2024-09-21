package com.group_3.cozyHaven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.model.PackageHotel;
import com.group_3.cozyHaven.model.PackageVehicle;
import com.group_3.cozyHaven.repository.PackageVehicleRepository;

@Service
public class PackageVehicleService {
	
	@Autowired
	private PackageVehicleRepository packageVehicleRepository;

	public PackageVehicle addVehicle(PackageVehicle packageVehicle) {
		return packageVehicleRepository.save(packageVehicle);
	}

}
