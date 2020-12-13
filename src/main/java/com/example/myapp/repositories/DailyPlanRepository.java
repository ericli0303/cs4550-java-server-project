package com.example.myapp.repositories;


import com.example.myapp.models.DailyPlan;
import com.example.myapp.models.MealPlan;
import com.example.myapp.models.User;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DailyPlanRepository extends CrudRepository<DailyPlan, Integer> {
    @Query(value = "SELECT * FROM daily_plan where meal_plan_id=:mpid", nativeQuery = true)
    public List<DailyPlan> findDailyPlansByMealPlanId(
            @Param("mpid") int mpid);

}
