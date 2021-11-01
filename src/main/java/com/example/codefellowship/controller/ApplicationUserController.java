package com.example.codefellowship.controller;

import com.example.codefellowship.models.ApplicationUser;
import com.example.codefellowship.models.Post;
import com.example.codefellowship.repository.ApplicationUserRepository;
import com.example.codefellowship.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ApplicationUserController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PostRepository postRepository;


    @GetMapping("/")
    public String getHomePage(Principal principal, Model model){
        if(principal == null){
            return "error.html";
        }
        else {
            model.addAttribute("username", principal.getName());
            return "index.html";
        }
    }
    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpUser(@RequestParam String username, @RequestParam String password, @RequestParam String firstName,
                             @RequestParam String lastName, @RequestParam String dateOfBirth, @RequestParam String bio){
        ApplicationUser appUser = new ApplicationUser(username, encoder.encode(password),  firstName,  lastName,  dateOfBirth,  bio);
        applicationUserRepository.save(appUser);
        return "login";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/logout")
    public String getLogoutPage(){
        return "index";
    }

    @GetMapping("/users/{id}")
    public String profile(@PathVariable Integer id, Model model) {
        ApplicationUser user = applicationUserRepository.findById(id).get();
        model.addAttribute("user", user);
        model.addAttribute("id", user.getId());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("dateOfBirth", user.getDateOfBirth());
        model.addAttribute("bio", user.getBio());

        return "profile";
    }

    @GetMapping("/profile")
    public String myprofile(Model model, Principal principal) {
        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("id", user.getId());
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("dateOfBirth", user.getDateOfBirth());
        model.addAttribute("bio", user.getBio());

        return "profile";
    }


    @PostMapping("/profile")
    public RedirectView postforprofile(Principal p, @RequestParam String body){
        Post newPost= new Post(body,applicationUserRepository.findByUsername(p.getName()));
        postRepository.save(newPost);
        return new RedirectView("/profile");
    }
    }
