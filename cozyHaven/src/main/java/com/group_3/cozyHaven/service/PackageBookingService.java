package com.group_3.cozyHaven.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Customer;
import com.group_3.cozyHaven.model.HolidayPackage;
import com.group_3.cozyHaven.model.PackageBooking;
import com.group_3.cozyHaven.repository.CustomerRepository;
import com.group_3.cozyHaven.repository.HolidayPackageRepository;
import com.group_3.cozyHaven.repository.PackageBookingRepository;

@Service
public class PackageBookingService {
	
	@Autowired
	private PackageBookingRepository packageBookingRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private HolidayPackageRepository holidayPackageRepository;
	
	
	public PackageBooking bookPackage(int holidayPackageId,int customerId) throws InvalidIdException, InputValidationException {
		Optional<Customer> customerOpt = customerRepository.findById(customerId);
		if (customerOpt.isEmpty()) {
			throw new InvalidIdException("Please sign Up to Book Hotels");
		}
		Customer customer = customerOpt.get();
		
		Optional<HolidayPackage> optionalHotel=holidayPackageRepository.findById(holidayPackageId);
		if(optionalHotel.isEmpty()) {
			throw new InputValidationException("Package Id is not correct");
		}
		HolidayPackage holidayPackage=optionalHotel.get();

		PackageBooking  packageBooking=new PackageBooking();
		packageBooking.setDateBooked(LocalDate.now());
		packageBooking.setCustomer(customer);
		packageBooking.setHolidayPackage(holidayPackage);
		
		PackageBooking booking=packageBookingRepository.save(packageBooking);
		return booking;
		
		
	}

}
