package com.xyz.EHub.controller;

import com.xyz.EHub.model.Users;
import com.xyz.EHub.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public String homePage(Principal p){
        if(p!=null){
            String email = p.getName();
            Users u = null;
            try {
                u = userService.getUserByUserName(email);
                if(u.getRole().equals("LEARNER")){
                    return "redirect:/learner/home";
                }
                return "redirect:/user/home";
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return "home";
    }
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute Users user , HttpSession session){
        boolean f =  userService.existEmailCheck(user.getEmail());
        if(f) {
            session.setAttribute("msg", "Email already exist");
        }
        else {
            Users u = userService.registerUser(user);
            if(u==null) {
                session.setAttribute("msg", "Something Wrong on Server");
            }
        }
        session.setAttribute("msg", "Sign Up  Successfully Done");
        return "redirect:/register";
    }
    @GetMapping("/register")
    public String signupPage(){
        return "signup";
    }
    @GetMapping("/course")
    public String coursePage(Principal p){
        if(p!=null){
            String email = p.getName();
            Users u = null;
            try {
                u = userService.getUserByUserName(email);
                if(u.getRole().equals("LEARNER")){
                    return "redirect:/learner/course";
                }
                return "redirect:/user/course";
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return "redirect:/learner/course";
    }
    @GetMapping("/blog")
    public String blogPage(Principal p){
        if(p!=null){
            String email = p.getName();
            Users u = null;
            try {
                u = userService.getUserByUserName(email);
                if(u.getRole().equals("LEARNER")){
                    return "redirect:/learner/blog";
                }
                return "redirect:/user/blog";
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return "redirect:/learner/blog";
    }
    @GetMapping("/about")
    public String aboutPage(Principal p){
        if(p!=null){
            String email = p.getName();
            Users u = null;
            try {
                u = userService.getUserByUserName(email);
                if(u.getRole().equals("LEARNER")){
                    return "redirect:/learner/about";
                }
                return "redirect:/user/about";
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return "redirect:/learner/about";
    }
}
