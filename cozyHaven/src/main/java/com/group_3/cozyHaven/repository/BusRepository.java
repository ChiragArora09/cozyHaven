package com.group_3.cozyHaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group_3.cozyHaven.model.Bus;

public interface BusRepository extends JpaRepository<Bus, Integer> {

	// select * from bus b JOIN bus_stop bs ON bs.bus_id=b.id JOIN stop s ON s.id=bs.stop_id where (s.stop_name="Bina" OR s.stop_name="Bhopal") AND b.id = (select b.id from bus_stop bs JOIN bus b ON b.id=bs.bus_id JOIN stop s ON s.id=bs.stop_id WHERE s.stop_name="Bina" AND b.id in (select b.id from bus b JOIN bus_stop bs ON bs.bus_id=b.id JOIN stop s ON s.id=bs.stop_id WHERE s.stop_name="Bhopal") AND bs.stop_number < (select bs.stop_number from bus b JOIN bus_stop bs ON bs.bus_id=b.id JOIN stop s ON s.id=bs.stop_id WHERE s.stop_name="Bhopal"));
	// select b.name, b.number, b.type, b.description, s.stopName, bs.stopNumber, bs.arrival, bs.departure, bs.distance FROM BusStop bs JOIN bs.bus JOIN bs.stop WHERE (s.stopName=?1 OR s.stopName=?2) AND b.id=(SELECT b.id FROM BusStop bs JOIN bs.bus JOIN bs.stop WHERE s.stopName=?1 AND b.id in (SELECT b.id FROM BusStop bs JOIN bs.stop JOIN bs.bus WHERE s.stopName=?2) AND bs.stopNumber < (SELECT bs.stopNumber from BusStop bs JOIN bs.bus JOIN bs.stop WHERE s.stopName=?2))
	@Query("select b.name, b.number, b.type, b.description, s.stopName, bs.stopNumber, bs.arrival, bs.departure, bs.distance FROM BusStop bs JOIN bs.bus b JOIN bs.stop s WHERE (s.stopName=?1 OR s.stopName=?2) AND b.id=(SELECT b.id FROM BusStop bs JOIN bs.bus b JOIN bs.stop s WHERE s.stopName=?1 AND b.id in (SELECT b.id FROM BusStop bs JOIN bs.stop s JOIN bs.bus b WHERE s.stopName=?2) AND bs.stopNumber < (SELECT bs.stopNumber from BusStop bs JOIN bs.bus b JOIN bs.stop s WHERE s.stopName=?2))")
	List<Object[]> getBusBetweenStops(String source, String destination);
	
}
