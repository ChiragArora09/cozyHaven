package com.group_3.cozyHaven.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group_3.cozyHaven.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
}
