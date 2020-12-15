package com.example.myapp.controllers;

import com.example.myapp.models.Following;
import com.example.myapp.models.User;
import com.example.myapp.services.FollowingService;
import com.example.myapp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "https://cs4550-f20-project.herokuapp.com", allowCredentials = "true", allowedHeaders = "*")
public class FollowingController {
    @Autowired
    FollowingService followingService;
    @Autowired
    UserService userService;


    @GetMapping("/api/followings")
    public List<Following> getAllFollowing() {
        return followingService.findAllFollowings();
    }

    @GetMapping("/api/follower/{uid}/followings")
    public List<Following> getFollowingsByFollower(
            @PathVariable("uid") int uid)  {
        return followingService.getFollowingsByFollower(uid);
    }

    @GetMapping("/api/follower/{uid}/followings/user")
    public List<User> getUserFollowingsByFollower(
            @PathVariable("uid") int uid)  {
        List<Following> followings = followingService.getFollowingsByFollower(uid);
        List<User> users = new ArrayList<User>();
        for(Following f: followings){
            User u = userService.getUserById(f.getCreatorId());
            u.setPassword("***");
            users.add(u);
        }
        return users;
    }

    @GetMapping("/api/creator/{uid}/followings/user")
    public List<User> getUserFollowingsByCreator(
            @PathVariable("uid") int uid)  {
        System.out.println("Getting User Followings By Creator");
        List<Following> followings = followingService.getFollowingsByCreator(uid);
        List<User> users = new ArrayList<User>();
        for(Following f: followings){
            User u = userService.getUserById(f.getFollowerId());
            u.setPassword("***");
            users.add(u);
        }
        return users;
    }

    @GetMapping("/api/followings/{fid}")
    public Following getFollowingById(
            @PathVariable("fid") int fid) {
        return followingService.getFollowingById(fid);
    }

    @GetMapping("/api/users/{uid}/recentfollowers")
    public List<User> getAllRecentFollowing(
            @PathVariable("uid") Integer creatorId
    ) {
        return followingService.getRecentFollowers(creatorId);
    }

    @DeleteMapping("/api/followings/{fid}")
    public void deleteFollowing(
            @PathVariable("fid") int fid) {
        followingService.deleteFollowing(fid);
    }

    @PostMapping("/api/followings")
    public Following addFollowing(
            @RequestBody Following following) {
        return followingService.addFollowing(following);
    }

}
