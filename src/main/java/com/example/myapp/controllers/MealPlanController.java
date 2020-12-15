package com.example.myapp.controllers;

import com.example.myapp.models.MealPlan;
import com.example.myapp.services.MealPlanService;

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

@RestController
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
public class MealPlanController {
    @Autowired
    MealPlanService mealPlanService;

    @GetMapping("/api/mealplans")
    public List<MealPlan> getAllMealPlans() {
        return mealPlanService.findAllMealPlans();
    }

    @GetMapping("/api/mealplans/{mid}")
    public MealPlan getMealPlanById(
            @PathVariable("mid") int mid) {
        return mealPlanService.getMealPlanById(mid);
    }

    @GetMapping("/api/mealplans/name/{name}")
    public List<MealPlan> getMealPlanByName(
            @PathVariable("name") String name) {
        return mealPlanService.getMealPlansByName(name);
    }

    @GetMapping("/api/creator/{creator}/mealplans")
    public List<MealPlan> getMealPlanByCreator(
            @PathVariable("creator") int cid) {
        return mealPlanService.getMealPlansByCreator(cid);
    }

    @GetMapping("/api/mealplans/type/{type}")
    public List<MealPlan> getMealPlanByType(
            @PathVariable("type") String type) {
        return mealPlanService.getMealPlansByType(type);
    }

    @DeleteMapping("/api/mealplans/{mid}")
    public void deleteMealPlan(
            @PathVariable("mid") int mid) {
        mealPlanService.deleteMealPlan(mid);
    }

    @PostMapping("/api/creator/{cid}/mealplan")
    public MealPlan createMealPlan(
            @PathVariable("cid") int cid,
            @RequestBody MealPlan mealPlan) {
        return mealPlanService.createMealPlan(cid, mealPlan);
    }

    @PutMapping("/api/mealplans/{mid}")
    public MealPlan updateMealPlan(
            @PathVariable("mid") int mid,
            @RequestBody MealPlan mealplan) {
        return mealPlanService.updateMealPlan(mid, mealplan);
    }

}
