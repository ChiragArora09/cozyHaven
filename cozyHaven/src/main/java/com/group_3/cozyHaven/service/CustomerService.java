package com.group_3.cozyHaven.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.enums.RoomType;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.Booking;
import com.group_3.cozyHaven.model.Customer;
import com.group_3.cozyHaven.model.Room;
import com.group_3.cozyHaven.model.User;
import com.group_3.cozyHaven.repository.BookingRepository;
import com.group_3.cozyHaven.repository.CustomerRepository;
import com.group_3.cozyHaven.repository.RoomRepository;
import com.group_3.cozyHaven.repository.UserRepository;


@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private Logger logger=LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired 
	private BookingRepository bookingRepository;

	public Customer addCustomer(Customer customer) {
		
		User user=customer.getUser();
		user.setRole("ROLE_CUSTOMER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user = userRepository.save(user); 
		customer.setUser(user);
		logger.info("adding customer to db" +customer);

		return customerRepository.save(customer);
		
	}

	public void validate(Customer customer) throws InputValidationException {
		if(customer == null)  {
			logger.error("Customer given is NULL, InputValidationException Thrown");
			throw new InputValidationException("Object cannot be null ");
		}

		if(customer.getFullname() == null || customer.getFullname().equals("")) {
			logger.error("Customer name given is NULL or blank, InputValidationException Thrown");
			throw new InputValidationException("Field customer.name cannot be blank ");
		}

		if(customer.getContactNumber() == null || customer.getContactNumber().equals("")) {
			logger.error("Customer contact given is NULL or blank, InputValidationException Thrown");
			throw new InputValidationException("Field customer.contact cannot be blank ");
		}
		
	}
	
	public Customer getById(int id) throws InputValidationException {
		Optional<Customer> option = customerRepository.findById(id);
		if(option.isEmpty()) {
			throw new InputValidationException("Invalid ID");
		}
		return option.get();
	}

	public void save(Customer customer) {
		customerRepository.save(customer);
	}

	public Customer updateCustomerInfo(Customer customer, int customerId) {
		Customer oldCustomer = customerRepository.findById(customerId).get();
		oldCustomer.setFullname(customer.getFullname());
		oldCustomer.setContactNumber(customer.getContactNumber());
		oldCustomer.setEmail(customer.getEmail());
		oldCustomer.setAddress(customer.getAddress());
		oldCustomer.setIdType(customer.getIdType());
		oldCustomer.setIdNumber(customer.getIdNumber());
		return customerRepository.save(oldCustomer);
	}



   
		

}
