package com.xyz.EHub.service;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class PhotoService {
    @Autowired
    private Cloudinary cloudinary;
    public String uploadVideo(MultipartFile file) throws IOException {
        // Generate a random UUID
        UUID uuid = UUID.randomUUID();

        // Convert UUID to string and remove hyphens
        String uniqueId = uuid.toString().replace("-", "");
        Map data =this.cloudinary.uploader().uploadLarge(file.getBytes(),Map.of("resource_type", "video",
                "public_id", uniqueId,"chunk_size", 6000000));
        return (String) data.get("url");
    }
    public String uploadPhoto(MultipartFile file) throws IOException {
        Map data =this.cloudinary.uploader().upload(file.getBytes(),Map.of());
        return (String) data.get("url");
    }
}
