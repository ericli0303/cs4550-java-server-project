package com.example.myapp.controllers;

import com.example.myapp.models.DailyPlan;
import com.example.myapp.models.Following;
import com.example.myapp.models.Meal;
import com.example.myapp.models.MealPlan;
import com.example.myapp.models.User;
import com.example.myapp.models.Widget;
import com.example.myapp.repositories.UserRepository;
import com.example.myapp.services.DailyPlanService;
import com.example.myapp.services.MealPlanService;
import com.example.myapp.services.MealService;
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
public class MealController {
    @Autowired
    MealService mealService;

    @GetMapping("/api/meals")
    public List<Meal> getAllMeals() {
        return mealService.findAllMeals();
    }

    @GetMapping("/api/meals/{mid}")
    public Meal getMealById(
            @PathVariable("mid") int mid) {
        return mealService.getMealById(mid);
    }

    @GetMapping("/api/dailyplans/{dpid}/meals")
    public List<Meal> getMealsByDailyPlanId(
            @PathVariable("dpid") int dpid) {
        return mealService.getMealsByDailyPlanId(dpid);
    }

    @DeleteMapping("/api/meals/{mid}")
    public void deleteMeal(
            @PathVariable("mid") int mid) {
        mealService.deleteMeal(mid);
    }

    @PostMapping("/api/dailyplans/{dpid}/meals")
    public Meal createMeal(
            @PathVariable("dpid") int dpid,
            @RequestBody Meal meal) {
        return mealService.createMeal(dpid, meal);
    }

    @PutMapping("/api/meals/{mid}")
    public Meal updateMeal(
            @PathVariable("mid") int mid,
            @RequestBody Meal meal) {
        return mealService.updateMeal(mid, meal);
    }
}
