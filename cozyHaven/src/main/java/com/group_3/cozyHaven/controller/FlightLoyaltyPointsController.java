package com.group_3.cozyHaven.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.dto.AddFlightLoyaltyPointsDto;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.service.FlightLoyaltyPointsService;
import com.group_3.cozyHaven.utility.GetId;

@RestController
@RequestMapping("/loyalty-points")
@CrossOrigin(origins = {"http://localhost:4200"})
public class FlightLoyaltyPointsController {
	
	@Autowired
	private FlightLoyaltyPointsService flightLoyaltyPointsService;
	
	@Autowired
	private GetId getId;
	
	@PostMapping("/add")
	public ResponseEntity<?> addLoyaltyPoints(Principal principal, @RequestBody AddFlightLoyaltyPointsDto dto) throws InputValidationException {
		String CustomerUsername = principal.getName();
		int customerId = getId.getIdByUsername(CustomerUsername);
		return ResponseEntity.ok(flightLoyaltyPointsService.updateLoyaltyPoints(dto, customerId));
	}

}
