package com.group_3.cozyHaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group_3.cozyHaven.model.FlightCity;

public interface FlightCityRepository extends JpaRepository<FlightCity, Integer>{

	List<FlightCity> findByFlightId(int flightid);

}
