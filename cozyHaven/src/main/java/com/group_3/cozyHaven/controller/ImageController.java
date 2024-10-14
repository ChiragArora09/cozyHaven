package com.group_3.cozyHaven.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.group_3.cozyHaven.dto.MessageDto;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Image;
import com.group_3.cozyHaven.service.ImageService;

import jakarta.persistence.criteria.Path;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ImageController {
	
	@Autowired
	private ImageService imageService;
	
	@PostMapping("/upload/{hotelId}/{roomId}")
	public ResponseEntity<?> uploadImage(@RequestParam("files") MultipartFile[] files,@PathVariable int hotelId, @PathVariable int roomId) throws IOException{
		List<String> uploadImage=imageService.uploadImage(files,hotelId,roomId);
		return ResponseEntity.ok(uploadImage);
	}
	
	@DeleteMapping("/delete/{imageId}")
	public  ResponseEntity<?> deleteImage(@PathVariable int imageId,MessageDto dto) {
        try {
			Image image= imageService.deleteImage(imageId);
			dto.setMsg("Image deleted");
			return ResponseEntity.ok(dto);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		
        
        
	}
	

	@GetMapping("/{roomId}/urls")
    public ResponseEntity<List<String>> getImageUrlsByRoomId(@PathVariable int roomId) {
        List<Image> images = imageService.getImagesByRoomId(roomId);

        if (images.isEmpty()) {
            return ResponseEntity.noContent().build(); // No images found
        }

        List<String> imageUrls = new ArrayList<>();
        for (Image image : images) {
            String imageUrl = "http://localhost:8082/image/view/" + image.getId();
            imageUrls.add(imageUrl);
        }

        return ResponseEntity.ok(imageUrls);
    	}

}
