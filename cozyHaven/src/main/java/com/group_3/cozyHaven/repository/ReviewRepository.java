package com.group_3.cozyHaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group_3.cozyHaven.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{

	@Query(value=" select r.id,r.comments,r.rating,r.star,h.hotel_name,h.location from service_provider s join hotel h on h.service_provider_id=s.id join review r on r.hotel_id=h.id where s.id=?",nativeQuery=true)
	List<Object[]> findBooking(int serviceProviderId);

}
