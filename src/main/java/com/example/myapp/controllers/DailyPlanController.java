package com.example.myapp.controllers;

import com.example.myapp.models.DailyPlan;
import com.example.myapp.models.Following;
import com.example.myapp.models.MealPlan;
import com.example.myapp.models.User;
import com.example.myapp.models.Widget;
import com.example.myapp.repositories.UserRepository;
import com.example.myapp.services.DailyPlanService;
import com.example.myapp.services.MealPlanService;
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
public class DailyPlanController {
    @Autowired
    DailyPlanService dailyPlanService;

    @GetMapping("/api/dailyplans")
    public List<DailyPlan> getAllDailyPlans() {
        return dailyPlanService.findAllDailyPlans();
    }

    @GetMapping("/api/dailyplans/{dpid}")
    public DailyPlan getDailyPlanById(
            @PathVariable("dpid") int dpid) {
        return dailyPlanService.getDailyPlanById(dpid);
    }

    @GetMapping("/api/mealplans/{mpid}/dailyplans")
    public List<DailyPlan> getDailyPlanByMealPlanId(
            @PathVariable("mpid") int mpid) {
        return dailyPlanService.getDailyPlansByMealPlanId(mpid);
    }

    @GetMapping("/api/dailyplans/name/{name}")
    public List<DailyPlan> getDailyPlanByName(
            @PathVariable("name") String name) {
        return dailyPlanService.getDailyPlansByName(name);
    }

    @DeleteMapping("/api/dailyplans/{dpid}")
    public void deleteDailyPlan(
            @PathVariable("dpid") int dpid) {
        dailyPlanService.deleteDailyPlan(dpid);
    }

    @PostMapping("/api/mealplans/{mpid}/dailyplans")
    public DailyPlan createDailyPlan(
            @PathVariable("mpid") int mpid,
            @RequestBody DailyPlan dailyPlan) {
        return dailyPlanService.createDailyPlan(mpid, dailyPlan);
    }

    @PutMapping("/api/dailyplans/{dpid}")
    public DailyPlan updateDailyPlan(
            @PathVariable("dpid") int dpid,
            @RequestBody DailyPlan dailyplan) {
        return dailyPlanService.updateDailyPlan(dpid, dailyplan);
    }

}
