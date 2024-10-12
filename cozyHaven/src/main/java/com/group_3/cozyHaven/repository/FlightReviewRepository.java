package com.group_3.cozyHaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group_3.cozyHaven.model.FlightReview;

public interface FlightReviewRepository extends JpaRepository<FlightReview, Integer> {

	@Query(value = "select distinct(fb.id), fr.description, fr.star, fb.date, fb.source, fb.destination, c.fullname, c.email from flight_review fr JOIN flight_booking fb ON fb.id=fr.flight_booking_id JOIN flight_seat_booking fsb ON fsb.flight_booking_id=fb.id JOIN flight_seat fs ON fs.id=fsb.flight_seat_id JOIN flight_class fc ON fc.id=fs.flight_class_id JOIN flight f ON f.id=fc.flight_id JOIN customer c ON c.id=fr.customer_id WHERE f.id=?1", nativeQuery = true)
	List<Object[]> getReviewsOnParticularFlight(int flightId);

}
