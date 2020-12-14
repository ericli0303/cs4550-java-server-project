package com.example.myapp.services;

import java.util.ArrayList;
import java.util.List;


import com.example.myapp.models.Following;
import com.example.myapp.models.User;
import com.example.myapp.repositories.FollowingRepository;
import com.example.myapp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowingService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FollowingRepository followingRepository;

    public List<Following> findAllFollowings() {
        return (List<Following>) followingRepository.findAll();
    }

    public Following addFollowing(Following following) {
        int creatorId = following.getCreatorId();
        int followerId = following.getFollowerId();
        List<Following> tempFollowings= followingRepository.findFollowingsByFollower(followerId);
        for(Following f: tempFollowings) {
            if(f.getCreatorId()==creatorId) {
                throw new IllegalArgumentException("Following Already Exists");
            }
        }
        return followingRepository.save(following);
    }

    public List<Following> getFollowingsByFollower(int follower) {
        List<Following> followings = followingRepository.findFollowingsByFollower(follower);
        return followings;
    }

    public List<User> getRecentFollowers(int creator) {
        List<Following> followings = followingRepository.findRecentFollowersForCreator(creator);
//        followings.sort(Comparator.comparing(Following::getTime));
        List<User> followers = new ArrayList<User>();
        for(int i = 0; i < followings.size(); i++) {
            followers.add(userRepository.findById(followings.get(i).getFollowerId()).get());
        }
        return followers;
    }


    public void deleteFollowing(int fid) {
        try {
            followingRepository.deleteById(fid);
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Delete Following failed");
        }
    }

    public Following getFollowingById(int fid) {
        return followingRepository.findById(fid).get();
    }





}
