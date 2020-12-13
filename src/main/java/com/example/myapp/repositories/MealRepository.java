package com.example.myapp.repositories;


import com.example.myapp.models.DailyPlan;
import com.example.myapp.models.Meal;
import com.example.myapp.models.MealPlan;
import com.example.myapp.models.User;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MealRepository extends CrudRepository<Meal, Integer> {
    @Query(value = "SELECT * FROM meals where daily_plan_id=:dpid", nativeQuery = true)
    public List<Meal> findMealsByDailyPlanId(
            @Param("dpid") int dpid);

}
