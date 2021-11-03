package com.example.codefellowship.controller;

import com.example.codefellowship.models.ApplicationUser;
import com.example.codefellowship.models.Post;
import com.example.codefellowship.repository.ApplicationUserRepository;
import com.example.codefellowship.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
           model.addAttribute("username", user.getUsername());
            return "index.html";
        }

    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView signUpUser(@RequestParam String username, @RequestParam String password, @RequestParam String firstName,
                             @RequestParam String lastName, @RequestParam String dateOfBirth, @RequestParam String bio){
        ApplicationUser appUser = new ApplicationUser(username, encoder.encode(password),  firstName,  lastName,  dateOfBirth,  bio);
        applicationUserRepository.save(appUser);
        return new RedirectView("/profile");
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

//    @GetMapping("/profile")
//    public String myprofile(Model model, Principal principal) {
//        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
//        model.addAttribute("user", user);
//        model.addAttribute("username", user.getUsername());
//        model.addAttribute("id", user.getId());
//        model.addAttribute("firstName", user.getFirstName());
//        model.addAttribute("lastName", user.getLastName());
//        model.addAttribute("dateOfBirth", user.getDateOfBirth());
//        model.addAttribute("bio", user.getBio());
//
//        return "profile";
//    }

    @GetMapping("/profile")
    public String myprofile(Model model, Principal principal) {
        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
        model.addAttribute("user",user);
        return "profile";
    }
    @PostMapping("/profile")
    public RedirectView postforprofile(Principal p, @RequestParam String body){
        Post newPost= new Post(body,applicationUserRepository.findByUsername(p.getName()));
        postRepository.save(newPost);
        return new RedirectView("/profile");
    }

    @GetMapping("/profile/{id}")
    public String FollowingProfile(@PathVariable("id") int id, Model model){
        ApplicationUser following = applicationUserRepository.findById(id).get();
        model.addAttribute("user",following);
        return ("profile.html");
    }
@GetMapping ("/suggest")
public String home(Model m, Principal p){
    List<ApplicationUser> allUsers= applicationUserRepository.findAll();
    ApplicationUser currentUser= applicationUserRepository.findByUsername(p.getName());
    List<ApplicationUser> followingList= currentUser.getFollowing();
    List<ApplicationUser> notFollowedUser= new ArrayList<>();
    allUsers.forEach((element)->{
        if ((! followingList.contains(element)) && element.getUsername() != currentUser.getUsername() ){
            notFollowedUser.add(element);
        }
    });
    m.addAttribute("users",notFollowedUser);
    return ("follow.html");
}


    @GetMapping("/follow/{id}")
    public RedirectView addFollower(@PathVariable("id") int id, Principal p){
        ApplicationUser usertoFollow = applicationUserRepository.findById(id).get();
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        currentUser.setFollowing(usertoFollow);
        applicationUserRepository.save(currentUser);
        return new RedirectView("/suggest");
    }
    @GetMapping("/feed")
    public String GetFeed( Principal p, Model model){
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        List<ApplicationUser> followList= currentUser.getFollowing();
        model.addAttribute("followings",followList);
        return "feed.html";
    }


    }
