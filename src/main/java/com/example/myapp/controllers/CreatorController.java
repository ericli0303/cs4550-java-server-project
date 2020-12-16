package com.example.myapp.controllers;

import com.example.myapp.models.User;
import com.example.myapp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
@CrossOrigin(origins = "https://cs4550-f20-project.herokuapp.com", allowCredentials = "true", allowedHeaders = "*")
public class CreatorController {
    @Autowired
    UserService userService;

    @GetMapping("/api/creators")
    public List<User> getAllCreators() {
        return userService.getCreators();
    }

    @GetMapping("/api/creators/{cid}")
    public User getCreatorsById(
            @PathVariable int cid) {
        return userService.getUserById(cid);
    }

}
