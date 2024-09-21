package com.group_3.cozyHaven.utility;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.model.User;
import com.group_3.cozyHaven.repository.UserRepository;

@Service
public class GetId {
	
	@Autowired
	private UserRepository userRepository;
	
	public int getIdByUsername(String username) {
		Optional<User> optional = userRepository.findByUsername(username); // get user object using the username
		User user = optional.get(); // insert into user 
		String role = user.getRole();
		int id = -1;
		if(role.equals("ROLE_SERVICE_PROVIDER")) {
			List<Object[]> slist = userRepository.getServiceProviderId(username);
			Object[] spid =  slist.get(0);
			id = (int) spid[0];
		}
		else if(role.equals("ROLE_CUSTOMER")) {
			List<Object[]> clist = userRepository.getCustomerId(username);
			Object[] cid = clist.get(0);
			id = (int) cid[0];
		}
		return id;
	}
}
