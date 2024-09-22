package com.group_3.cozyHaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group_3.cozyHaven.model.BusBooking;

public interface BusBookingRepository extends JpaRepository<BusBooking, Integer> {

	@Query("SELECT distinct(bb.id), bb.date, bb.source, bb.destination, b.type, b.name, b.number, bb.amount, bb.status FROM BusSeatBooking bsb JOIN bsb.busBooking bb JOIN bsb.busSeat bs JOIN bs.bus b ON bb.customer.id=?1")
	List<Object[]> getBookings(int customerId);

}
