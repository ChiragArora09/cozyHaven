package com.group_3.cozyHaven;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.group_3.cozyHaven.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.csrf(AbstractHttpConfigurer::disable)
	        .authorizeHttpRequests(auth -> auth
	        		
	            // Permit all for token generation and signup
	            .requestMatchers("/auth/token").permitAll()
	            .requestMatchers("/auth/signup").permitAll()
	            
	            // Role-based access 
	            .requestMatchers("/admin/hello").hasRole("ADMIN")
	            .requestMatchers("/customer/hello").hasRole("CUSTOMER")
	            .requestMatchers("/service-provider/hello").hasRole("SERVICE_PROVIDER") 
	            
	            // Allow service provider to add rooms, hotels, etc.
	            .requestMatchers("service-provider/add").permitAll()
	            .requestMatchers("/hotel/add/{serviceProviderId}").hasRole("SERVICE_PROVIDER")
	            .requestMatchers("/room/add/{serviceProviderId}/{hotelId}").hasRole("SERVICE_PROVIDER")
	            .requestMatchers("/image/upload/{hotelId}/{roomId}").hasRole("SERVICE_PROVIDER")
	            .requestMatchers("/amenities/add/{roomId}").hasRole("SERVICE_PROVIDER")
	            .requestMatchers("/room/update/{roomId}").hasRole("SERVICE_PROVIDER")
	            .requestMatchers("/room/updateAvailabity").hasRole("SERVICE_PROVIDER")
	            
	            //allow customers to fetch and book hotels rooms
	            .requestMatchers("/customer/add").permitAll()
	            .requestMatchers("hotel/search").hasRole("CUSTOMER")
	            .requestMatchers("hotel/{location}").hasRole("CUSTOMER")
	            .requestMatchers("hotel/rooms/{hotelId}").hasRole("CUSTOMER")
	            .requestMatchers("hotel/amenities/{roomId}").hasRole("CUSTOMER")
	         // .requestMatchers("/customer/booking/{customerId}/{roomId}").hasRole("CUSTOMER")
	            .requestMatchers("/room/{hotelId}/{roomType}").hasRole("CUSTOMER")
	            .requestMatchers("/booking//room/{roomId}/{customerId}").hasRole("CUSTOMER")
	            .requestMatchers("/booking/customer/{customerId}").hasAnyRole("CUSTOMER","SERVICE_PROVIDER")
	            .requestMatchers("/reviews/{customerId}/{hotelId}").hasRole("CUSTOMER")
	            .requestMatchers("/booking/cancel/{customerId}").hasRole("CUSTOMER")
	            


	            .requestMatchers("/flight/add").hasRole("SERVICE_PROVIDER")
//                .requestMatchers("/flight/route").hasRole("SERVICE_PROVIDER")
//	            .requestMatchers("/flight/add/flight-route").hasRole("SERVICE_PROVIDER")
//	            .requestMatchers("/flight/add/flight-class").hasRole("SERVICE_PROVIDER")
//	            .requestMatchers("/flight/add/flight-seat").hasRole("SERVICE_PROVIDER")
//                .requestMatchers("/flight/flight-between-station").hasRole("CUSTOMER")
                
                .requestMatchers("/bus/add-bus").hasRole("SERVICE_PROVIDER")
                

                .anyRequest().permitAll()


	        )
	        .sessionManagement(session -> session
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        );

	    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(getEncoder());
        return authProvider;
	}
	
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
   }
	
	@Bean 
	PasswordEncoder getEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
