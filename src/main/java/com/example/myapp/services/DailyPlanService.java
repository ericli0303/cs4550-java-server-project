package com.example.myapp.services;

import java.util.ArrayList;
import java.util.List;


import com.example.myapp.models.DailyPlan;
import com.example.myapp.repositories.DailyPlanRepository;
import com.example.myapp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailyPlanService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DailyPlanRepository dailyPlanRepository;

    public List<DailyPlan> findAllDailyPlans() {
        return (List<DailyPlan>) dailyPlanRepository.findAll();
    }

    public DailyPlan createDailyPlan(int mpid, DailyPlan dp) {
        dp.setMealPlanId(mpid);
        return dailyPlanRepository.save(dp);
    }

    public List<DailyPlan> getDailyPlansByName(String name) {
        List<DailyPlan> dailyPlans = (List<DailyPlan>) dailyPlanRepository.findAll();
        List<DailyPlan> foundDailyPlans = new ArrayList<DailyPlan>();
        for(DailyPlan d: dailyPlans) {
            if(d.getName().contains(name)) {
                foundDailyPlans.add(d);
            }
        }
        return foundDailyPlans;
    }

    public List<DailyPlan> getDailyPlansByMealPlanId(int mpid) {
        List<DailyPlan> dailyPlans = dailyPlanRepository.findDailyPlansByMealPlanId(mpid);
        return dailyPlans;
    }

    public void deleteDailyPlan(int dpid) {
        try {
            dailyPlanRepository.deleteById(dpid);
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Delete DailyPlan failed");
        }
    }

    public DailyPlan getDailyPlanById(int dpid) {
        return dailyPlanRepository.findById(dpid).get();
    }

    public DailyPlan updateDailyPlan(int dpid, DailyPlan dailyPlan) {
        try{
            DailyPlan oldDailyPlan = dailyPlanRepository.findById(dpid).get();
            oldDailyPlan.setName(dailyPlan.getName());
            dailyPlanRepository.save(oldDailyPlan);
            return oldDailyPlan;
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Update DailyPlan failed");
        }
    }

}
