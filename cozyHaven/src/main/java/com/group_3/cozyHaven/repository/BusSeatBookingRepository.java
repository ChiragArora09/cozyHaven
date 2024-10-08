package com.group_3.cozyHaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.group_3.cozyHaven.model.BusSeatBooking;

public interface BusSeatBookingRepository extends JpaRepository<BusSeatBooking, Integer>{

	@Query("SELECT bb.amount, bs.seatType FROM BusSeatBooking bsb JOIN bsb.busBooking bb JOIN bsb.busSeat bs WHERE bb.id=?1")
	List<Object[]> getPaymentInfo(int bid);

	@Modifying
	@Transactional
	@Query("DELETE from BusSeatBooking bsb WHERE bsb.busBooking.id=?1")
	Object deleteSeats(int bid);

}
