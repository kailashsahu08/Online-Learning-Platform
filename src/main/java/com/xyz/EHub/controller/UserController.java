package com.xyz.EHub.controller;


import com.xyz.EHub.dto.CourseDto;
import com.xyz.EHub.model.Course;
import com.xyz.EHub.model.Users;
import com.xyz.EHub.service.ICourseService;
import com.xyz.EHub.service.IUserService;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@MultipartConfig
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ICourseService iCourseService;
    @ModelAttribute
    public Users getUser(Principal p , Model m) {
        String email = p.getName();
        Users user = null;
        try {
            user = userService.getUserByUserName(email);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        m.addAttribute("user", user);
        return user;
    }

    @GetMapping("/profile")
    public String profilePage(Principal p , Model m){
        Users u = getUser(p, m);
        return "profile";
    }

    @GetMapping("/home")
    public String userHomePage(Principal p , Model m){
        Users u = getUser(p, m);
        return "home";
    }
    @GetMapping("/course")
    public String userCoursePage(Principal p , Model m){
        Users u = getUser(p, m);
        List<Course> courses = iCourseService.getAllCourses();
        m.addAttribute("allCourse",courses);
        return "course";
    }
    @GetMapping("/myCourse")
    public String myCoursePage(Principal p , Model m){
        Users u = getUser(p, m);
        List<Course> courses = u.getCourse();
        m.addAttribute("myCourse",courses);
        return "myCourse";
    }

    @GetMapping("/blog")
    public String userBlogPage(Principal p , Model m){
        Users u = getUser(p, m);
        return "blog";
    }
    @GetMapping("/about")
    public String userAboutPage(Principal p , Model m){
        Users u = getUser(p, m);
        return "about";
    }
    @GetMapping("/create")
    public String userCreatePage(Principal p , Model m){
        Users u = getUser(p, m);
        return "create";
    }
    @PostMapping("/createCourse")
    public String createCourse(@RequestParam("title") String title,
                               @RequestParam("description") String description,
                               @RequestParam("video") MultipartFile video,
                               @RequestParam("image") MultipartFile image, Principal p, Model m){
        Users user = getUser(p,m);
        CourseDto courseDto = new CourseDto(title,description,image,video);
        String str = iCourseService.registerCource(courseDto,user);
        return  "create";
    }
    @GetMapping("/viewCourse/{courseId}")
    public String moveToContent(@PathVariable int courseId,Principal p, Model m){
        Users user = getUser(p,m);
        try {
            boolean b = iCourseService.checkEnrollOrNot(courseId,user);
            Course course = iCourseService.getCourseById(courseId);
            m.addAttribute("course",course);
            if(b)
                return "material";
            else
                return "enroll";
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "material";
    }
}
