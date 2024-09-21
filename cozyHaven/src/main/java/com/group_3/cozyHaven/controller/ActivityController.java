package com.group_3.cozyHaven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.Activity;
import com.group_3.cozyHaven.service.ActivityService;

@RestController
@RequestMapping("/package")
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	@PostMapping("/add/activity/{dayId}")
	public ResponseEntity<?> addActivity(@PathVariable int dayId,@RequestBody Activity activity){
		try {
			Activity activityDay=activityService.addActivity(dayId,activity);
			return ResponseEntity.ok(activityDay);
		} catch (InputValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}

}
