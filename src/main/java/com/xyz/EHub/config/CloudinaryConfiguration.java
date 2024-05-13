package com.xyz.EHub.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfiguration {
    @Bean
    public Cloudinary getCloudinary(){
        Map map = new HashMap<>();
        map.put("cloud_name", "dlkihhtby");
        map.put("api_key", "593414891225959");
        map.put("api_secret", "XPGk13KyrFuE29TJ7gLxlqYQpa8");
        map.put("secure", true);
        Cloudinary cloudinary = new Cloudinary(map);
        return cloudinary;
    }
}
