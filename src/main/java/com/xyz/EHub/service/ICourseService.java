package com.xyz.EHub.service;

import com.xyz.EHub.dto.CourseDto;
import com.xyz.EHub.model.Course;
import com.xyz.EHub.model.Users;

import java.util.List;

public interface ICourseService {
    public  String registerCource(CourseDto courseDto, Users user);
    public List<Course> getAllCourses();
    public boolean checkEnrollOrNot(int courseId,Users user)throws Exception;
    public Course getCourseById(int id)throws Exception;
    public Course updateCourse (Course course)throws Exception;
}
