package com.example.myapp.services;

import java.util.List;


import com.example.myapp.models.Favorite;
import com.example.myapp.repositories.FavoriteRepository;
import com.example.myapp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FavoriteRepository favoriteRepository;

    public List<Favorite> findAllFavorites() {
        return (List<Favorite>) favoriteRepository.findAll();
    }

    public Favorite addFavorite(Favorite favorite) {
        int mealPlanId = favorite.getMealPlanId();
        int followerId = favorite.getFollowerId();
        List<Favorite> tempFavorites= favoriteRepository.findFavoritesByFollowerId(followerId);
        for(Favorite f: tempFavorites) {
            if(f.getMealPlanId()==mealPlanId) {
                throw new IllegalArgumentException("Following Already Exists");
            }
        }
        return favoriteRepository.save(favorite);
    }

    public List<Favorite> getRecentFavorites(int followerId) {
        List<Favorite> favorites = favoriteRepository.findRecentFavoritesByFollowerId(followerId);
//        followings.sort(Comparator.comparing(Following::getTime));
        return favorites;
    }


    public void deleteFavorite(int fid) {
        try {
            favoriteRepository.deleteById(fid);
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Delete Favorite failed");
        }
    }

    public List<Favorite> getFavoritesByFollowerId(int fid) {
        return favoriteRepository.findFavoritesByFollowerId(fid);
    }

    public Favorite getFavoriteById(int fid) {
        return favoriteRepository.findById(fid).get();
    }





}
