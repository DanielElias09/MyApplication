package com.example.myapplication.models;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private String RecipeName;
    private String RecipeIngredients;
    private String RecipeMethod;
    private String Category;
    private String UserName;

    public Recipe(String recipeName, String recipeIngredients, String recipeMethod, String category, String userName) {
        RecipeName = recipeName;
        RecipeIngredients = recipeIngredients;
        RecipeMethod = recipeMethod;
        Category = category;
        UserName = userName;
    }

    public void setRecipeMethod(String recipeMethod) {
        RecipeMethod = recipeMethod;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getRecipeMethod() {
        return RecipeMethod;
    }

    public String getUserName() {
        return UserName;
    }

    public  String getRecipeName(){ return RecipeName; }

    public String getRecipeIngredients(){
        return RecipeIngredients;
    }

    public String getCategory() { return Category; }

    public void setRecipeName(String recipeName) {
        RecipeName = recipeName;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        RecipeIngredients = recipeIngredients;
    }

    public void setCategory(String category) {
        Category = category;
    }

}
