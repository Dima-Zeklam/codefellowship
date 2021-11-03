package com.example.codefellowship.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Post {
    private LocalDate createdAt;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    private String body;

   @ManyToOne
   @JoinColumn(name="Post_appUser")
    private ApplicationUser appUser;
    public Post() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ApplicationUser getAppUser() {
        return appUser;
    }

    public void setAppUser(ApplicationUser appUser) {
        this.appUser = appUser;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public Post(String body, ApplicationUser appUser) {
        this.createdAt = LocalDate.now();
        this.body = body;
        this.appUser = appUser;
    }
}
