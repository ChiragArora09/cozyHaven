package com.group_3.cozyHaven.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.Admin;
import com.group_3.cozyHaven.model.User;
import com.group_3.cozyHaven.repository.AdminRepository;
import com.group_3.cozyHaven.repository.UserRepository;

@Service
public class AdminService {
	

	@Autowired
	private AdminRepository adminRepository;
	
	private Logger logger=LoggerFactory.getLogger(AdminService.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Admin addAdmin(Admin admin) {
		User user=admin.getUser();
		user.setRole("ROLE_ADMIN");
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user = userRepository.save(user);
		admin.setUser(user);
		logger.info("adding employee to db" +admin);

		return adminRepository.save(admin);
	}

	public void validate(Admin admin) throws InputValidationException {
		if(admin == null)  {
			logger.error("Admin given is NULL, InputValidationException Thrown");
			throw new InputValidationException("Object cannot be null ");
		}

		if(admin.getName() == null || admin.getName().equals("")) {
			logger.error("Admin name given is NULL or blank, InputValidationException Thrown");
			throw new InputValidationException("Field admin.name cannot be blank ");
		}

		if(admin.getPassword() == null || admin.getPassword().equals("")) {
			logger.error("Admin contact given is NULL or blank, InputValidationException Thrown");
			throw new InputValidationException("Field admin.contact cannot be blank ");
		}
		
	}

	

}
