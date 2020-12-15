package com.example.myapp.services;

import java.util.List;
import java.util.Optional;

import com.example.myapp.models.User;
import com.example.myapp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findUserByUsername(String username) {
        List<User> foundUsers = userRepository.findUserByUsername(username);
        if(!foundUsers.isEmpty()){
            return foundUsers.get(0);
        } else {
            return null;
        }
    }

    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User createUser(User user) {
        List<User> foundUsers = userRepository.findUserByUsername(user.getUsername());
        if(!foundUsers.isEmpty()){
            throw new IllegalArgumentException("Username already taken");
        }
        return userRepository.save(user);
    }

    public boolean isUserAndPass(User user) {
        List<User> foundUsers = userRepository.findUserByUsername(user.getUsername());
        if(!foundUsers.isEmpty()){
            return foundUsers.get(0).isSame(user);
        }
        else {
            return false;
        }
    }

    public User updateUser(int userId, User user) {
        User oldUser = userRepository.findById(userId).get();
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        return userRepository.save(oldUser);
    }

    public boolean isUser(String username) {
        List<User> foundUsers = userRepository.findUserByUsername(username);
        if(!foundUsers.isEmpty()){
            return true;
        } else {
            return false;
        }
    }

    public void deleteUser(int userId) {
        try {
            userRepository.deleteById(userId);
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Delete Widget failed");
        }
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).get();
    }

    /*
        Creator Stuff
     */

    public List<User> getCreators() {
        List<User> creators = userRepository.findUserByType("creator");
        return creators;
    }



}
