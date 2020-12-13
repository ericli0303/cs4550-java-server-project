package com.example.myapp.controllers;

import com.example.myapp.models.User;
import com.example.myapp.models.Widget;
import com.example.myapp.repositories.UserRepository;
import com.example.myapp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpSession;

@RestController
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/api/users")
    public User register(
            HttpSession session,
            @RequestBody User user) {
        User newUser = userService.createUser(user);
        newUser.setPassword("***");
        session.setAttribute("profile", newUser);
        return newUser;
    }

    @PostMapping("/api/users/signIn")
    public User signIn(
            HttpSession session,
            @RequestBody User user) {
        if(userService.isUserAndPass(user)) {
            session.setAttribute("profile", user);
            return user;
        } else {
            return null;
        }
    }

    /**
     * Given a session, returns the user's profile with the password taken out
     * @param session the http session
     * @return the User
     */
    @PostMapping("/api/profile")
    public User profile(HttpSession session) {
        User profile = (User) session.getAttribute("profile");
        profile.setPassword("***");
        return profile;
    }

    /**
     * Gets if the the user's username exists and whether the user's password matches for the username
     * @param user the passed in user
     * @return true if they match, false otherwise
     */
    @GetMapping("/api/is/users/password")
    public boolean getIsUserAndPass(
            @RequestBody User user) {
        return userService.isUserAndPass(user);
    }

    /**
     * Gets if the Username exists in the database
     * @param username the username as a string
     * @return true if it exists already, false otherwise
     */
    @GetMapping("/api/is/user/{username}")
    public boolean getIsUser(
            @PathVariable("username") String username) {
        return userService.isUser(username);
    }

    /**
     * Updates a user's username
     */
    @PutMapping("/api/users/{userId}")
    public User updateUser(
            @PathVariable("userId") int userId,
            @RequestBody User user) {
        return userService.updateUser(userId, user);
    }

    /**
     * Deletes a user
     */
    @DeleteMapping("/api/users/{userId}")
    public void deleteUser(
            @PathVariable("userId") int userId) {
        userService.deleteUser(userId);
    }
}
