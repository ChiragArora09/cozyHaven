package com.group_3.cozyHaven.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.enums.ServiceType;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.ServiceProvider;
import com.group_3.cozyHaven.model.User;
import com.group_3.cozyHaven.repository.ServiceProviderRepository;
import com.group_3.cozyHaven.repository.UserRepository;

@Service
public class ServiceProviderService {
	
	@Autowired
	private ServiceProviderRepository serviceProviderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public ServiceProvider addServiceProvider(ServiceProvider serviceProvider) {
		User user = serviceProvider.getUser();
		ServiceType serviceType = serviceProvider.getServiceType();
		String type = serviceType.toString().toUpperCase();
		user.setRole("ROLE_"+type+"_SERVICE_PROVIDER");
//		user.setRole("ROLE_SERVICE_PROVIDER");
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepository.save(user); 
		serviceProvider.setUser(user);
		
		return serviceProviderRepository.save(serviceProvider) ;
	}
	
	public ServiceProvider getById(int id) throws InputValidationException {
		Optional<ServiceProvider> option = serviceProviderRepository.findById(id);
		if(option.isEmpty()) {
			throw new InputValidationException("Invalid ID");
		}
		return option.get();
	}
	
}
