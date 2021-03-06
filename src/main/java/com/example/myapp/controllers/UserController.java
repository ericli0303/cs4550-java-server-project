package com.example.myapp.controllers;

import com.example.myapp.models.User;
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
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
@CrossOrigin(origins = "https://cs4550-f20-project.herokuapp.com", allowCredentials = "true", allowedHeaders = "*")
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
//        newUser.setPassword("***");
        session.setAttribute("profile", newUser);
        return newUser;
    }

    @PostMapping("/api/users/signin")
    public User signIn(
            HttpSession session,
            @RequestBody User user) {
        if(userService.isUserAndPass(user)) {
            User tempUser = userService.findUserByUsername(user.getUsername());
            session.setAttribute("profile", tempUser);
            return tempUser;
        } else {
            return new User();
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
//        profile.setPassword("***");
        System.out.println(profile.getUsername());
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

    @GetMapping("/api/user/{userId}")
    public User getUserById(
            @PathVariable("userId") int userId) {
        return userService.getUserById(userId);
    }
}

//mysql://b8fb7732ecea35:2a51decc@us-cdbr-east-02.cleardb.com/heroku_42d9c73607813e0?reconnect=true
