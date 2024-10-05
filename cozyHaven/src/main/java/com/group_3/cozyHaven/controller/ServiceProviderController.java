package com.group_3.cozyHaven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.dto.MessageDto;
import com.group_3.cozyHaven.model.ServiceProvider;
import com.group_3.cozyHaven.service.ServiceProviderService;

@RestController
@RequestMapping("/service-provider")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ServiceProviderController {
	
	@Autowired
	private ServiceProviderService serviceProviderService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addServiceProvider(@RequestBody ServiceProvider serviceProvider, MessageDto dto){
		return ResponseEntity.ok(serviceProviderService.addServiceProvider(serviceProvider));
	}
	
}
