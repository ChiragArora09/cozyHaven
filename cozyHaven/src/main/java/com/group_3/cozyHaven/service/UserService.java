package com.group_3.cozyHaven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.model.User;
import com.group_3.cozyHaven.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User adduser(User user) {
		
		//encrypt the password 
				String plainText = user.getPassword();
				//encode this  
				String encryptedPassword  = passwordEncoder.encode(plainText);
				//attach this to User obj 
				user.setPassword(encryptedPassword);

				return userRepository.save(user);
		
	}


}
