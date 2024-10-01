package com.group_3.cozyHaven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.JwtUtil;
import com.group_3.cozyHaven.dto.MessageDto;
import com.group_3.cozyHaven.model.User;
import com.group_3.cozyHaven.repository.UserRepository;
import com.group_3.cozyHaven.service.UserService;


@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	 
    @Autowired
    private UserService userService;
 
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
	private PasswordEncoder passwordEncoder;
    
    @PostMapping("/auth/token")
    public MessageDto createAuthenticationToken(@RequestBody User authenticationRequest,MessageDto dto) throws Exception {
 
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Incorrect username or password", e);
        }
 
        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
        System.out.println(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        dto.setMsg(jwt);
        return dto;
    }
    
}
