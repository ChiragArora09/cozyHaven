package com.group_3.cozyHaven.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.enums.RoleType;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.Customer;
import com.group_3.cozyHaven.model.User;
import com.group_3.cozyHaven.repository.CustomerRepository;
import com.group_3.cozyHaven.repository.UserRepository;


@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	private Logger logger=LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	private UserService userService;

	public Customer addCustomer(Customer customer) {
		
		User user=customer.getUser();
		user.setRole(RoleType.CUSTOMER);
		user=userService.adduser(user);
		customer.setUser(user);
		logger.info("adding employee to db" +customer);

		return customerRepository.save(customer);
		
	}

	public void validate(Customer customer) throws InputValidationException {
		if(customer == null)  {
			logger.error("Employee given is NULL, InputValidationException Thrown");
			throw new InputValidationException("Object cannot be null ");
		}

		if(customer.getFullname() == null || customer.getFullname().equals("")) {
			logger.error("Employee name given is NULL or blank, InputValidationException Thrown");
			throw new InputValidationException("Field employee.name cannot be blank ");
		}

		if(customer.getContactNumber() == null || customer.getContactNumber().equals("")) {
			logger.error("Employee contact given is NULL or blank, InputValidationException Thrown");
			throw new InputValidationException("Field employee.contact cannot be blank ");
		}
		
	}

}
