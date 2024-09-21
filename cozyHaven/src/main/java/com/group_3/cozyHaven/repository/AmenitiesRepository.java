package com.group_3.cozyHaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group_3.cozyHaven.model.Amenities;

@Repository
public interface AmenitiesRepository extends JpaRepository<Amenities,Integer>{
	
	@Query("SELECT a.id, a.spa, a.gym, a.swimmingPool, a.parkingArea, a.freeWifi, a.breakfast, a.breafastLunc, a.room.id FROM Amenities a WHERE a.room.id = ?1")
	List<Amenities> findByRoomId(int roomId);

}
