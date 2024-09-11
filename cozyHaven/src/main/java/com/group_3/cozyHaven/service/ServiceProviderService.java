package com.group_3.cozyHaven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.enums.RoleType;
import com.group_3.cozyHaven.model.ServiceProvider;
import com.group_3.cozyHaven.model.User;
import com.group_3.cozyHaven.repository.ServiceProviderRepository;

@Service
public class ServiceProviderService {
	
	@Autowired
	private ServiceProviderRepository serviceProviderRepository;
	
	@Autowired
	private UserService userService;
	
	public Object addServiceProvider(ServiceProvider serviceProvider) {
		User user = serviceProvider.getUser();
		user.setRole(RoleType.SERVICE_PROVIDER);
		user = userService.adduser(user);
		
		serviceProvider.setUser(user);
		
		return serviceProviderRepository.save(serviceProvider) ;
	}
	

}
