package com.example.myapp.controllers;

import com.example.myapp.models.Ingredient;

import com.example.myapp.services.IngredientService;

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

@RestController
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "https://cs4550-f20-project.herokuapp.com", allowCredentials = "true", allowedHeaders = "*")
public class IngredientController {
    @Autowired
    IngredientService ingredientService;

    @GetMapping("/api/ingredients")
    public List<Ingredient> getAllIngredients() {
        return ingredientService.findAllIngredients();
    }

    @GetMapping("/api/ingredients/{iid}")
    public Ingredient getIngredientById(
            @PathVariable("iid") int iid) {
        return ingredientService.getIngredientById(iid);
    }

    @GetMapping("/api/meals/{mid}/ingredients")
    public List<Ingredient> getIngredientsByDailyPlanId(
            @PathVariable("mid") int mid) {
        return ingredientService.getIngredientsByMealId(mid);
    }

    @DeleteMapping("/api/ingredients/{iid}")
    public void deleteIngredient(
            @PathVariable("iid") int iid) {
        ingredientService.deleteIngredient(iid);
    }

    @PostMapping("/api/meals/{mid}/ingredients")
    public Ingredient createIngredient(
            @PathVariable("mid") int mid,
            @RequestBody Ingredient ingredient) {
        return ingredientService.createIngredient(mid, ingredient);
    }

    @PutMapping("/api/ingredients/{iid}")
    public Ingredient updateIngredient(
            @PathVariable("iid") int iid,
            @RequestBody Ingredient ingredient) {
        return ingredientService.updateIngredient(iid, ingredient);
    }
}
