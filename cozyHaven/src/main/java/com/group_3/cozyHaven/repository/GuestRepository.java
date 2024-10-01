package com.group_3.cozyHaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group_3.cozyHaven.model.HotelGuest;

public interface GuestRepository extends JpaRepository<HotelGuest, Integer>{

	List<HotelGuest> findByBookingId(int id);

}
