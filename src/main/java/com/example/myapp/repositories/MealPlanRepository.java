package com.example.myapp.repositories;


import com.example.myapp.models.MealPlan;
import com.example.myapp.models.User;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MealPlanRepository extends CrudRepository<MealPlan, Integer> {
    @Query(value = "SELECT * FROM meal_plan", nativeQuery = true)
    public List<MealPlan> findMealPlansByName(
            @Param("title") String title);

    @Query(value = "SELECT * FROM meal_plan where creator_id=:cid", nativeQuery = true)
    public List<MealPlan> findMealPlansByCreator(
            @Param("cid") int cid);

    @Query(value = "SELECT * FROM meal_plan where diet=:type", nativeQuery = true)
    public List<MealPlan> findMealPlansByType(
            @Param("type") String type);
}
