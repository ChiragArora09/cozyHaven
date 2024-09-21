package com.group_3.cozyHaven.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.model.PackageHotel;
import com.group_3.cozyHaven.service.PackageHotelService;

@RestController
@RequestMapping("/package")
public class PackageHotelController {
	
	@Autowired
	private PackageHotelService packageHotelService;
	
	@PostMapping("/hotel/add")
	public PackageHotel addhotel(@RequestBody PackageHotel packageHotel) {
		return packageHotelService.addHotel(packageHotel);
		
	}

}
