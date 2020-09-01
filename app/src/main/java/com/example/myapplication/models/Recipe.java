package com.example.myapplication.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Recipe {
    @PrimaryKey
    @NonNull
    private Long id;
    private String RecipeName;
    private String RecipeIngredients;
    private String RecipeMethod;
    private String Category;
    private String UserName;
    private String ImagePath;

    public Recipe(String recipeName, String recipeIngredients, String recipeMethod, String category, String userName, String imagePath) {
        RecipeName = recipeName;
        RecipeIngredients = recipeIngredients;
        RecipeMethod = recipeMethod;
        Category = category;
        UserName = userName;
        ImagePath = imagePath;
    }

    public Recipe(Long id, String recipeName, String recipeIngredients, String recipeMethod, String category, String userName, String imagePath) {
        this(recipeName, recipeIngredients, recipeMethod, category, userName, imagePath);
        this.id = id;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
