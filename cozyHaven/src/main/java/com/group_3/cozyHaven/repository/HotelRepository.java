package com.group_3.cozyHaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group_3.cozyHaven.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer>{
	
	@Query("SELECT h from Hotel h where h.location=?1")
	List<Hotel> findByLocation(String location);

}
