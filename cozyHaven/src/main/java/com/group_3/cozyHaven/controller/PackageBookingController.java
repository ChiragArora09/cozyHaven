package com.group_3.cozyHaven.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.PackageBooking;
import com.group_3.cozyHaven.service.PackageBookingService;
import com.group_3.cozyHaven.utility.GetId;

@RestController
@RequestMapping("/booking")
public class PackageBookingController {
	
	@Autowired
	private PackageBookingService packageBookingService;
	
	@Autowired
	private GetId getId;
	
	@PostMapping("/package/{holidayPackageId}")
	public ResponseEntity<?> bookPackage (@PathVariable int holidayPackageId,Principal principal){
		String username=principal.getName();
		int customerId=getId.getIdByUsername(username);
		try {
			PackageBooking booking= packageBookingService.bookPackage(holidayPackageId,customerId);
			return ResponseEntity.ok(booking);
		} catch (InvalidIdException | InputValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
