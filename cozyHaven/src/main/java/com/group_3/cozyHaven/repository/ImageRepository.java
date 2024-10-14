package com.group_3.cozyHaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group_3.cozyHaven.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image,Integer> {

	List<Image> findByRoomId(int roomId);

}
