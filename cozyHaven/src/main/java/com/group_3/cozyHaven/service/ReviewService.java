package com.group_3.cozyHaven.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Customer;
import com.group_3.cozyHaven.model.Hotel;
import com.group_3.cozyHaven.model.Review;
import com.group_3.cozyHaven.model.Room;
import com.group_3.cozyHaven.repository.CustomerRepository;
import com.group_3.cozyHaven.repository.HotelRepository;
import com.group_3.cozyHaven.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	public Review addReview(int customerId,int hotelId,Review review) throws InvalidIdException {
		
		Optional<Customer> customerOpt = customerRepository.findById(customerId);
		if (customerOpt.isEmpty()) {
			throw new InvalidIdException("You have not booked this Hotel");
		}
		Customer customer = customerOpt.get();
		
		Optional<Hotel> hotelOpt = hotelRepository.findById(hotelId);
		if (hotelOpt.isEmpty()) {
			throw new InvalidIdException("You have not booked this hotel");
		}
		Hotel hotel = hotelOpt.get();
		
		
		review.setComments(review.getComments());
		review.setRating(review.getRating());
		review.setStar(review.getStar());
		review.setCustomer(customer);
		review.setHotel(hotel);
		review.setCustomer(customer);
		return reviewRepository.save(review);
		
		
		
	}

}
