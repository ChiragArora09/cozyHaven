package com.group_3.cozyHaven.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.dto.ReviewDto;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Hotel;
import com.group_3.cozyHaven.model.Review;
import com.group_3.cozyHaven.service.ReviewService;
import com.group_3.cozyHaven.utility.GetId;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private GetId getId;
	
	@PostMapping("/{hotelId}")
	public ResponseEntity<?> addReview(Principal principal,@PathVariable int hotelId,@RequestBody Review review){
		Review reviews;
		try {
			String username=principal.getName();
			int customerId=getId.getIdByUsername(username);
			reviews = reviewService.addReview(customerId,hotelId,review);
			return ResponseEntity.ok(reviews);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@GetMapping("/all")
	public List<ReviewDto> viewReview(Principal principal){
		String username=principal.getName();
		int serviceProviderId=getId.getIdByUsername(username);
		List<ReviewDto> review=reviewService.getAllReviews(serviceProviderId);
		return review;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteReview(@PathVariable int id,Principal principal){
		try {
			String username=principal.getName();
			int serviceProviderId=getId.getIdByUsername(username);

			reviewService.deleteReview(id,serviceProviderId);
		    return ResponseEntity.ok("Review Deleted");
		} catch (InputValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	} 
	
	}
