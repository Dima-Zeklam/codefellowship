package com.example.codefellowship.models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class ApplicationUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;
    private String password;

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String bio;

    public ApplicationUser() {
    }

    public ApplicationUser( String username, String password, String firstName, String lastName, String dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    @OneToMany(mappedBy = "appUser")
    List<Post> post;

    @ManyToMany
    @JoinTable(
            name="followed_stream",
            joinColumns = {@JoinColumn(name="followers_id ")},
            inverseJoinColumns = {@JoinColumn(name=" following_id")}
    )
    public List<ApplicationUser> following;

    @ManyToMany(mappedBy="following", fetch = FetchType.EAGER)
    public List<ApplicationUser> follower;

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public List<ApplicationUser> getFollowing() {
        return following;
    }

    public void setFollowing(ApplicationUser addfollowing) {
        this.following.add(addfollowing);
    }

    public List<ApplicationUser> getFollower() {
        return follower;
    }


    public void setFollower(List<ApplicationUser> follower) {
        this.follower = follower;
    }
}
