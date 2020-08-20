package com.example.myapplication.models;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private int id;
    private String RecipeName;
    private String RecipeIngredients;
    private String RecipeMethod;
    private String Category;

    public Recipe(String name, String recipeIngredients, String recipeMethod, String category){

        RecipeName = name;
        RecipeIngredients = recipeIngredients;
        RecipeMethod = recipeMethod;
        Category = category;

    }

    public  String getRecipeName(){ return RecipeName; }

    public String getRecipeIngredients(){
        return RecipeIngredients;
    }

    public String getRecipe(){
        return RecipeMethod;
    }

    public String getCategory() { return Category; }

    public void setRecipeName(String recipeName) {
        RecipeName = recipeName;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        RecipeIngredients = recipeIngredients;
    }


    public void setRecipe(String recipeMethod) {
        RecipeMethod = recipeMethod;
    }

    public void setCategory(String category) {
        Category = category;
    }

}
