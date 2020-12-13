package com.example.myapp.controllers;

import com.example.myapp.models.DailyPlan;
import com.example.myapp.models.Following;
import com.example.myapp.models.Ingredient;

import com.example.myapp.models.User;
import com.example.myapp.models.Widget;
import com.example.myapp.repositories.UserRepository;
import com.example.myapp.services.DailyPlanService;
import com.example.myapp.services.IngredientService;
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
