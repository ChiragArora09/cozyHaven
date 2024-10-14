package com.group_3.cozyHaven.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.dto.HolidayPackageDto;
import com.group_3.cozyHaven.dto.PackageDetailsDto;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.Day;
import com.group_3.cozyHaven.model.Extra;
import com.group_3.cozyHaven.model.HolidayPackage;
import com.group_3.cozyHaven.model.PackageBooking;
import com.group_3.cozyHaven.model.PackageHotel;
import com.group_3.cozyHaven.service.HolidayPackageService;
import com.group_3.cozyHaven.utility.GetId;

@RestController
@RequestMapping("/package")
@CrossOrigin(origins = {"http://localhost:4200"})
public class HolidayPackageController {
	
	@Autowired
	private HolidayPackageService holidayPackageService;
	
	
	@PostMapping("/holiday/add/{packageHotelId}")
	public ResponseEntity<?> addPackage(@PathVariable int packageHotelId ,@RequestBody HolidayPackage holidayPackage) {
		
		try {
			HolidayPackage holiday = holidayPackageService.addPackage(packageHotelId,holidayPackage);
			return ResponseEntity.ok(holiday);
		} catch (InputValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@PostMapping("/add/{holidayPackageId}")
	public ResponseEntity<?> addDay(@PathVariable int holidayPackageId,@RequestBody Day day){
		try {
			Day days=holidayPackageService.addDay(holidayPackageId,day);
			return ResponseEntity.ok(days);
		} catch (InputValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@PostMapping("/add/extra/{holidayPackageId}")
	public ResponseEntity<?> addExtra(@PathVariable int holidayPackageId, @RequestBody Extra extra){
		try {
			Extra extras=holidayPackageService.addExtra(holidayPackageId,extra);
			return ResponseEntity.ok(extras);
		} catch (InputValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
	}
	}
	
	@PostMapping("/search")
	public List<HolidayPackageDto> searchPackage(@RequestBody HolidayPackage holidayPackage,Principal principal) {
		String username=principal.getName();
		return holidayPackageService.searchPackage(holidayPackage,username);
		
		
	}
	
	@GetMapping("/detailsView/{holidayPackageId}")
	public ResponseEntity<?> viewDetails(@PathVariable int holidayPackageId){
           try {
             List<PackageDetailsDto> packageDetailsDto=holidayPackageService.viewDetails(holidayPackageId);
             return ResponseEntity.ok(packageDetailsDto);
			} catch (InputValidationException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
	
			}
	}
}
