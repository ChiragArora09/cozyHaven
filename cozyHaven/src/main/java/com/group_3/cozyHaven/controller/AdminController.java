package com.group_3.cozyHaven.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.dto.MessageDto;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.Admin;
import com.group_3.cozyHaven.model.Customer;
import com.group_3.cozyHaven.service.AdminService;
import com.group_3.cozyHaven.service.CustomerService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addEmployee(@RequestBody Admin admin,MessageDto dto) { //reading the i/p
			
			try {
				adminService.validate(admin);
			} catch (InputValidationException e) {
				dto.setMsg(e.getMessage());
				 return ResponseEntity.badRequest().body(dto); 
			} 
			return ResponseEntity.ok(adminService.addAdmin(admin)); 
		}

	

}
