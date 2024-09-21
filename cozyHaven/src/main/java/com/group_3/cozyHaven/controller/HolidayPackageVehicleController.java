package com.group_3.cozyHaven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.HolidayPackageVehicle;
import com.group_3.cozyHaven.service.HolidayPackageVehicleService;

@RestController
@RequestMapping("/package")
public class HolidayPackageVehicleController {
	
	@Autowired
	private HolidayPackageVehicleService holidayPackageVehicleService;
	
	@PostMapping("/holiday/vehicle/{holidayPackageId}/{packageVehicleId}")
	public ResponseEntity<?> addVehicleToPackage(@PathVariable int holidayPackageId,@PathVariable int packageVehicleId,@RequestBody HolidayPackageVehicle holidayPackageVehicle){
		try {
			HolidayPackageVehicle hpv=holidayPackageVehicleService.addVehicleToPackage(holidayPackageId,packageVehicleId,holidayPackageVehicle);
			return ResponseEntity.ok(hpv);
		} catch (InputValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}

	
}
