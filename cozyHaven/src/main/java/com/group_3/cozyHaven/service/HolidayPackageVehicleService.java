package com.group_3.cozyHaven.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.HolidayPackage;
import com.group_3.cozyHaven.model.HolidayPackageVehicle;
import com.group_3.cozyHaven.model.PackageVehicle;
import com.group_3.cozyHaven.repository.HolidayPackageRepository;
import com.group_3.cozyHaven.repository.HolidayPackageVehicleRepository;
import com.group_3.cozyHaven.repository.PackageVehicleRepository;

@Service
public class HolidayPackageVehicleService {
	
	@Autowired
	private HolidayPackageRepository holidayPackageRepository;
	
	@Autowired
	private HolidayPackageVehicleRepository holidayPackageVehicleRepository;
	
	@Autowired
	private PackageVehicleRepository packageVehicleRepository;


	public HolidayPackageVehicle addVehicleToPackage(int holidayPackageId,int packageVehicleId,HolidayPackageVehicle holidayPackageVehicle) throws InputValidationException {
		Optional<HolidayPackage> optionalHotel=holidayPackageRepository.findById(holidayPackageId);
		if(optionalHotel.isEmpty()) {
			throw new InputValidationException("Package Id is not correct");
		}
		HolidayPackage holidayPackage=optionalHotel.get();
		holidayPackageVehicle.setHolidayPackage(holidayPackage);
		
		Optional<PackageVehicle> optionalVehicle=packageVehicleRepository.findById(packageVehicleId);
		if(optionalHotel.isEmpty()) {
			throw new InputValidationException("Vehicle Id is not correct");
		}
		PackageVehicle packageVehicle=optionalVehicle.get();
		holidayPackageVehicle.setPackageVehicle(packageVehicle);
		
		
		
		return holidayPackageVehicleRepository.save(holidayPackageVehicle);
		
	}

}
