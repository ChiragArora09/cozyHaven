package com.group_3.cozyHaven.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Hotel;
import com.group_3.cozyHaven.model.Image;
import com.group_3.cozyHaven.repository.HotelRepository;
import com.group_3.cozyHaven.repository.ImageRepository;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;
    
    @Autowired
    private HotelRepository hotelRepository;
    private final String folderPath = "C:\\Users\\ADMIN\\Desktop\\HotelImage";

    public List<String> uploadImage(MultipartFile[] files, int hotelId) throws IOException {
        List<String> uploadedFilePaths = new ArrayList<>();
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        if (optionalHotel.isEmpty()) {
            throw new RuntimeException("Hotel not found with id: " + hotelId);
        }
        Hotel hotel = optionalHotel.get();
        for (MultipartFile file : files) {
            String originalName = file.getOriginalFilename();
            if (originalName == null || originalName.isEmpty()) {
                throw new RuntimeException("File name is empty or null for one of the files.");
            }

            String hotelFolderPath = folderPath + File.separator + hotelId;
            File hotelDirectory = new File(hotelFolderPath);
            if (!hotelDirectory.exists()) {
                boolean dirCreated = hotelDirectory.mkdirs();
                if (!dirCreated) {
                    throw new IOException("Failed to create directory: " + hotelFolderPath);
                }
            }
            String filepath = hotelFolderPath + File.separator + originalName;
            Image image = new Image();
            image.setImageName(originalName);
            image.setFilePath(filepath);
            image.setHotel(hotel);
            imageRepository.save(image);
            try {
                file.transferTo(new File(filepath));
                System.out.println("File saved to: " + filepath); 
                uploadedFilePaths.add("File uploaded successfully to: "+ filepath); 
            } catch (IOException e) {
                throw new IOException("Failed to upload file: " + e.getMessage(), e);
            }
        }

        return uploadedFilePaths;
    }

	public void deleteImage(int imageId) throws InvalidIdException {
		
		Optional<Image> optional=imageRepository.findById(imageId);
		if(optional.isEmpty()) {
			throw new InvalidIdException("Invalid Id Given");
		}
	
		Image image=optional.get();
	    File file=new File(image.getFilePath());
	    if(file.exists()) {
	    	if (!file.delete()) {
	           	System.out.println("failed to delete");
	        }
	    }
	   imageRepository.deleteById(imageId);
	    }
}
