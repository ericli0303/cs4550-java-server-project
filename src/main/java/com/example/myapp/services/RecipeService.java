package com.example.myapp.services;

import java.util.List;


import com.example.myapp.models.DailyPlan;
import com.example.myapp.models.Recipe;
import com.example.myapp.repositories.RecipeRepository;
import com.example.myapp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RecipeRepository RecipeRepository;

    public List<Recipe> findAllRecipes() {
        return (List<Recipe>) RecipeRepository.findAll();
    }

    public Recipe createRecipe(int mid, Recipe m) {
        m.setMealId(mid);
        return RecipeRepository.save(m);
    }

    public void deleteRecipe(int rid) {
        try {
            RecipeRepository.deleteById(rid);
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Delete Recipe failed");
        }
    }

    public Recipe getRecipeById(int rid) {
        return RecipeRepository.findById(rid).get();
    }

    public Recipe updateRecipe(int rid, Recipe recipe) {
        try{
            Recipe oldRecipe = RecipeRepository.findById(rid).get();
            oldRecipe.setServingSize(recipe.getServingSize());
            oldRecipe.setSpoonRecipeId(recipe.getSpoonRecipeId());
            oldRecipe.setServingUnit(recipe.getServingUnit());
            RecipeRepository.save(oldRecipe);
            return oldRecipe;
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Update Recipe failed");
        }
    }

    public List<Recipe> getRecipesByMealId(int mid) {
        List<Recipe> Recipes = RecipeRepository.findRecipesByMealId(mid);
        return Recipes;
    }



}
