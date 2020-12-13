package com.example.myapp.repositories;


import com.example.myapp.models.DailyPlan;
import com.example.myapp.models.Meal;
import com.example.myapp.models.MealPlan;
import com.example.myapp.models.Recipe;
import com.example.myapp.models.User;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
    @Query(value = "SELECT * FROM recipes where meal_id=:mid", nativeQuery = true)
    public List<Recipe> findRecipesByMealId(
            @Param("mid") int mid);

}
