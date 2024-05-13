package com.xyz.EHub.service;

import com.xyz.EHub.dto.CourseDto;
import com.xyz.EHub.exception.CourseNotFoundException;
import com.xyz.EHub.model.Course;
import com.xyz.EHub.model.Users;
import com.xyz.EHub.repository.CourseRepository;
import com.xyz.EHub.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService{
    @Autowired
    private PhotoService photoService;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public String registerCource(CourseDto courseDto,Users user) {

        try {
            // Assuming photoService is working correctly
            String imgUrl = photoService.uploadPhoto(courseDto.getImg());
            String videoUrl = photoService.uploadVideo(courseDto.getContent());
            Course course = new Course();
            course.setTitle(courseDto.getTitle());
            course.setDescription(courseDto.getDescription());
            course.setImageUrl(imgUrl);
            course.setTotalLike(0);
            course.setVideoUrl(videoUrl);

            // Add the course to the user's list of courses
            course.getUser().add(user);
            user.getCourse().add(course);

            // Save the course and the user in the same transaction
            courseRepository.save(course);

            return "Data Registered Successfully";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "There Was a Problem Registering Data";
        }
    }

    @Override
    public List<Course> getAllCourses() {

        return courseRepository.findAll();
    }

    @Override
    public boolean checkEnrollOrNot(int courseId, Users user)throws Exception {
        List<Course> myCourceList = user.getCourse();
        Optional<Course> course = courseRepository.findById(courseId);
        if(course.isPresent()){
            return  myCourceList.contains(course.get());
        }
        throw new CourseNotFoundException("Course Id Is Not Present");
    }

    @Override
    public Course getCourseById(int id) throws Exception {
        Optional<Course> c = courseRepository.findById(id);
        if(c.isEmpty())
            throw new CourseNotFoundException("Course Not Present On this Id");
        return c.get();
    }

    @Override
    public Course updateCourse(Course course) throws Exception {

        return courseRepository.save(course);
    }
}
