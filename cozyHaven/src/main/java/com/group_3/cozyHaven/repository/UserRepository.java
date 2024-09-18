package com.group_3.cozyHaven.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group_3.cozyHaven.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
       
	Optional<User> findByUsername(String username);

	@Query(value = "select sp.id from user u JOIN service_provider sp ON u.id=sp.user_id WHERE u.username=?1", nativeQuery = true)
	List<Object[]> getServiceProviderId(String username);

	@Query(value = "select c.id from user u JOIN customer c on c.user_id=u.id WHERE u.username=?1", nativeQuery = true)
	List<Object[]> getCustomerId(String username);

}



