package com.xyz.EHub.controller;


import com.xyz.EHub.model.Course;
import com.xyz.EHub.model.Users;
import com.xyz.EHub.service.ICourseService;
import com.xyz.EHub.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/learner")
public class LearnerControllerClass {
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
    @GetMapping("/home")
    public String gotoLearnerHomePage(Principal p,Model m){
        Users user = getUser(p,m);
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
    @GetMapping("/viewCourse/{courseId}")
    public String moveToContent(@PathVariable int courseId, Principal p, Model m){
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
    @GetMapping("/viewCourse/enrollCourse/{courseId}")
    public String enrollCourse(@PathVariable int courseId ,Principal p, Model m){
        Users user = getUser(p,m);
        try{
            Course c = iCourseService.getCourseById(courseId);
            c.getUser().add(user);
            user.getCourse().add(c);
            Course updatedCourse = iCourseService.updateCourse(c);
            m.addAttribute("course",updatedCourse);
            return "material";
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "material";
    }
    @GetMapping("/profile")
    public String profilePage(Principal p , Model m){
        Users u = getUser(p, m);
        return "profile";
    }
}
