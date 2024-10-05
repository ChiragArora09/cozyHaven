package com.group_3.cozyHaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.group_3.cozyHaven.model.FlightSeatBooking;

public interface FlightSeatBookingRepository extends JpaRepository<FlightSeatBooking, Integer>{
	
	@Query("SELECT fb.amount, fs.flightSeatType, fc.type FROM FlightSeatBooking fsb JOIN fsb.flightBooking fb JOIN fsb.flightSeat fs JOIN fs.flightClass fc WHERE fb.id=?1")
	List<Object[]> getPaymentInfo(int bid);

	@Modifying
	@Transactional
	@Query("DELETE FROM FlightSeatBooking fsb WHERE fsb.flightBooking.id=?1")
	Object deleteSeats(int bid);

	@Query("select sum(flp.total) FROM FlightLoyaltyPoints flp WHERE flp.serviceProvider.id=?1 AND flp.customer.id=?2")
	List<Object[]> getLoyaltyPoints(int serviceProviderId, int customerId);

}
