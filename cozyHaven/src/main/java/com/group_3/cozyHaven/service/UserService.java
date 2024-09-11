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
		String plainText = user.getPassword();  
		String encryptedPassword  = passwordEncoder.encode(plainText);
		user.setPassword(encryptedPassword);

		return userRepository.save(user);
	}
}
