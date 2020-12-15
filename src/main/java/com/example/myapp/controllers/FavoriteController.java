package com.example.myapp.controllers;

import com.example.myapp.models.Favorite;
import com.example.myapp.models.MealPlan;
import com.example.myapp.services.FavoriteService;
import com.example.myapp.services.MealPlanService;

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
public class FavoriteController {
    @Autowired
    FavoriteService favoriteService;
    @Autowired
    MealPlanService mealPlanService;


    @GetMapping("/api/favorites")
    public List<Favorite> getAllFavorites() {
        return favoriteService.findAllFavorites();
    }

    @GetMapping("/api/followers/{uid}/favorites")
    public List<Favorite> getFavoritesByFollowerId(
            @PathVariable("uid") int uid)  {
        return favoriteService.getFavoritesByFollowerId(uid);
    }

    @GetMapping("/api/followers/{uid}/favorites/mealplans")
    public List<MealPlan> getFavoriteMealPlansByFollowerId(
            @PathVariable("uid") int uid)  {
        List<Favorite> favorites = favoriteService.getFavoritesByFollowerId(uid);
        List<MealPlan> mealPlans = new ArrayList<MealPlan>();
        for(Favorite f: favorites) {
            MealPlan m = mealPlanService.getMealPlanById(f.getMealPlanId());
            mealPlans.add(m);
        }
        return mealPlans;
    }

    @GetMapping("/api/favorites/{fid}")
    public Favorite getFavoriteById(
            @PathVariable("fid") int fid) {
        return favoriteService.getFavoriteById(fid);
    }

    @GetMapping("/api/followers/{uid}/recentfavorites")
    public List<MealPlan> getAllRecentFavorites(
            @PathVariable("uid") Integer fid
    ) {
        return favoriteService.getRecentFavorites(fid);
    }

    @DeleteMapping("/api/favorites/{fid}")
    public void deleteFavorite(
            @PathVariable("fid") int fid) {
        favoriteService.deleteFavorite(fid);
    }

    @PostMapping("/api/favorites")
    public Favorite addFavorite(
            @RequestBody Favorite favorite) {
        return favoriteService.addFavorite(favorite);
    }

}
