package com.group_3.cozyHaven.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.group_3.cozyHaven.dto.MessageDto;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.Customer;
import com.group_3.cozyHaven.service.CustomerService;
import com.group_3.cozyHaven.utility.GetId;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private GetId getId;
	
	
	@PostMapping("/add")
	public ResponseEntity<?> addEmployee(@RequestBody Customer customer,MessageDto dto) { //reading the i/p
		
		try {
			customerService.validate(customer);
		} catch (InputValidationException e) {
			dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto); 
		} 
		return ResponseEntity.ok(customerService.addCustomer(customer)); 	
	}
	
	
	@PostMapping("/image/upload")
	public void addCustomerImage(Principal principal, @RequestParam MultipartFile file) throws InputValidationException {

		/*Fetch Customer */
		String CustomerUsername = principal.getName();
		int customerId = getId.getIdByUsername(CustomerUsername);
		Customer customer = customerService.getById(customerId);
		 
		String location = "C:/Users/Chirag Arora/OneDrive/Desktop/JAVA ANGULAR/cozyHavenClient/cozyhaven-client/src/assets";

		try {
			Files.copy(file.getInputStream(), Path.of(location, file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			customer.setProfilePicture(file.getOriginalFilename());
			customer.setImagePath(Path.of(location, file.getOriginalFilename()).toString());
			customerService.save(customer); 

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("my-info")
	public Customer getCustomerInfo(Principal principal) throws InputValidationException {
		String CustomerUsername = principal.getName();
		int customerId = getId.getIdByUsername(CustomerUsername);
		return customerService.getById(customerId);
	}
	
	
}
