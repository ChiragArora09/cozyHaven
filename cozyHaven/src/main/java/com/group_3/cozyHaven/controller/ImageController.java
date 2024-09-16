package com.group_3.cozyHaven.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.group_3.cozyHaven.dto.MessageDto;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.service.ImageService;

@RestController
@RequestMapping("/image")
public class ImageController {
	
	@Autowired
	private ImageService imageService;
	
	@PostMapping("/upload/{hotelId}/{roomId}")
	public ResponseEntity<?> uploadImage(@RequestParam MultipartFile[] file,@PathVariable int hotelId, @PathVariable int roomId) throws IOException{
		List<String> uploadImage=imageService.uploadImage(file,hotelId,roomId);
		return ResponseEntity.ok(uploadImage);
	}
	
	@DeleteMapping("/delete/{imageId}")
	public  ResponseEntity<?> deleteImage(@PathVariable int imageId,MessageDto dto) {
        try {
			imageService.deleteImage(imageId);
			dto.setMsg("Image deleted");
			return ResponseEntity.ok(dto);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		
        
	}

}
