package com.xyz.EHub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private  String title;
    private  String description;
    private MultipartFile img;
    private MultipartFile content;
}
