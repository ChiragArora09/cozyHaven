package com.group_3.cozyHaven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.model.PackageVehicle;
import com.group_3.cozyHaven.service.PackageVehicleService;

@RestController
@RequestMapping("/package")
public class PackageVehicleController {
	
	@Autowired
	private PackageVehicleService packageVehicleService;

	@PostMapping("/vehicle")
	public PackageVehicle addVehicle(@RequestBody PackageVehicle packageVehicle){
		 return packageVehicleService.addVehicle(packageVehicle);
	}
}
