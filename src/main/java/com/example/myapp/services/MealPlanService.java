package com.example.myapp.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


import com.example.myapp.models.MealPlan;
import com.example.myapp.models.User;
import com.example.myapp.models.Widget;
import com.example.myapp.repositories.FollowingRepository;
import com.example.myapp.repositories.MealPlanRepository;
import com.example.myapp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealPlanService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MealPlanRepository mealPlanRepository;

    public List<MealPlan> findAllMealPlans() {
        return (List<MealPlan>) mealPlanRepository.findAll();
    }

    public MealPlan createMealPlan(int cid, MealPlan mp) {
        mp.setCreatorId(cid);
        return mealPlanRepository.save(mp);
    }

    public List<MealPlan> getMealPlansByName(String name) {
        List<MealPlan> mealPlans = mealPlanRepository.findMealPlansByName(name);
        List<MealPlan> foundMealPlans = new ArrayList<MealPlan>();
        for(MealPlan m: mealPlans) {
            if(m.getName().contains(name)) {
                foundMealPlans.add(m);
            }
        }
        return foundMealPlans;
    }

    public List<MealPlan> getMealPlansByCreator(int cid) {
        List<MealPlan> mealPlans = mealPlanRepository.findMealPlansByCreator(cid);
        return mealPlans;
    }

    public List<MealPlan> getMealPlansByType(String type) {
        List<MealPlan> mealPlans = mealPlanRepository.findMealPlansByType(type);
        return mealPlans;
    }

    public void deleteMealPlan(int mid) {
        try {
            mealPlanRepository.deleteById(mid);
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Delete MealPlan failed");
        }
    }

    public MealPlan getMealPlanById(int mid) {
        return mealPlanRepository.findById(mid).get();
    }

    public MealPlan updateMealPlan(int mid, MealPlan mealPlan) {
        try{
            MealPlan oldMealPlan = mealPlanRepository.findById(mid).get();
            oldMealPlan.setName(mealPlan.getName());
            oldMealPlan.setDiet(mealPlan.getDiet());
            oldMealPlan.setTime(mealPlan.getTime());
            mealPlanRepository.save(oldMealPlan);
            return oldMealPlan;
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Update MealPlan failed");
        }
    }

}
