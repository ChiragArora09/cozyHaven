package com.group_3.cozyHaven.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.group_3.cozyHaven.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}



