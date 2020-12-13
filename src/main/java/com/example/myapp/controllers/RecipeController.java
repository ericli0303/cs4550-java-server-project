package com.example.myapp.controllers;

import com.example.myapp.models.DailyPlan;
import com.example.myapp.models.Following;
import com.example.myapp.models.Recipe;

import com.example.myapp.models.User;
import com.example.myapp.models.Widget;
import com.example.myapp.repositories.UserRepository;
import com.example.myapp.services.DailyPlanService;
import com.example.myapp.services.RecipeService;
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
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    @GetMapping("/api/recipes")
    public List<Recipe> getAllRecipes() {
        return recipeService.findAllRecipes();
    }

    @GetMapping("/api/recipes/{rid}")
    public Recipe getRecipeById(
            @PathVariable("rid") int rid) {
        return recipeService.getRecipeById(rid);
    }

    @GetMapping("/api/meals/{mid}/recipes")
    public List<Recipe> getRecipesByDailyPlanId(
            @PathVariable("mid") int mid) {
        return recipeService.getRecipesByMealId(mid);
    }

    @DeleteMapping("/api/recipes/{rid}")
    public void deleteRecipe(
            @PathVariable("rid") int rid) {
        recipeService.deleteRecipe(rid);
    }

    @PostMapping("/api/meals/{mid}/recipes")
    public Recipe createRecipe(
            @PathVariable("mid") int mid,
            @RequestBody Recipe recipe) {
        return recipeService.createRecipe(mid, recipe);
    }

    @PutMapping("/api/recipes/{rid}")
    public Recipe updateRecipe(
            @PathVariable("rid") int rid,
            @RequestBody Recipe recipe) {
        return recipeService.updateRecipe(rid, recipe);
    }
}