package com.example.myapp.services;

import java.util.List;


import com.example.myapp.models.DailyPlan;
import com.example.myapp.models.Meal;
import com.example.myapp.repositories.MealRepository;
import com.example.myapp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MealRepository mealRepository;

    public List<Meal> findAllMeals() {
        return (List<Meal>) mealRepository.findAll();
    }

    public Meal createMeal(int dpid, Meal m) {
        m.setDailyPlanId(dpid);
        return mealRepository.save(m);
    }

    public void deleteMeal(int mid) {
        try {
            mealRepository.deleteById(mid);
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Delete Meal failed");
        }
    }

    public Meal getMealById(int mid) {
        return mealRepository.findById(mid).get();
    }

    public Meal updateMeal(int mid, Meal meal) {
        try{
            Meal oldMeal = mealRepository.findById(mid).get();
            oldMeal.setName(meal.getName());
            mealRepository.save(oldMeal);
            return oldMeal;
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Update Meal failed");
        }
    }

    public List<Meal> getMealsByDailyPlanId(int dpid) {
        List<Meal> meals = mealRepository.findMealsByDailyPlanId(dpid);
        return meals;
    }



}
