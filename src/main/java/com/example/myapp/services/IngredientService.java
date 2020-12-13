package com.example.myapp.services;

import java.util.List;


import com.example.myapp.models.DailyPlan;
import com.example.myapp.models.Ingredient;
import com.example.myapp.repositories.IngredientRepository;
import com.example.myapp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    IngredientRepository IngredientRepository;

    public List<Ingredient> findAllIngredients() {
        return (List<Ingredient>) IngredientRepository.findAll();
    }

    public Ingredient createIngredient(int mid, Ingredient m) {
        m.setMealId(mid);
        return IngredientRepository.save(m);
    }

    public void deleteIngredient(int iid) {
        try {
            IngredientRepository.deleteById(iid);
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Delete Ingredient failed");
        }
    }

    public Ingredient getIngredientById(int iid) {
        return IngredientRepository.findById(iid).get();
    }

    public Ingredient updateIngredient(int iid, Ingredient ingredient) {
        try{
            Ingredient oldIngredient = IngredientRepository.findById(iid).get();
            oldIngredient.setServingSize(ingredient.getServingSize());
            oldIngredient.setSpoonIngredientId(ingredient.getSpoonIngredientId());
            oldIngredient.setServingUnit(ingredient.getServingUnit());
            IngredientRepository.save(oldIngredient);
            return oldIngredient;
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Update Ingredient failed");
        }
    }

    public List<Ingredient> getIngredientsByMealId(int mid) {
        List<Ingredient> Ingredients = IngredientRepository.findIngredientsByMealId(mid);
        return Ingredients;
    }



}
