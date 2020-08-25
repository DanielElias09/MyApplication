package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {
    
    SQLiteDatabase db;

    public RecipeDAO(SQLiteDatabase db) {
        this.db = db;
    }
    public Long insert(Recipe recipe){
        Long newRecipeID = insert(recipe.getRecipeName(), recipe.getRecipeIngredients(), recipe.getRecipeMethod(), recipe.getCategory(), recipe.getUserName(), recipe.getImagePath());
        return newRecipeID;
    }

    private Long insert(String recipeName, String recipeIngredients, String recipeMethod, String category, String userName, String imagePath) {
        ContentValues values = new ContentValues();
        values.put(Config.KEY_RECIPE_NAME, recipeName);
        values.put(Config.KEY_RECIPE_INGREDIENTS, recipeIngredients);
        values.put(Config.KEY_RECIPE_METHOD, recipeMethod);
        values.put(Config.KEY_RECIPE_CATEGORY, category);
        values.put(Config.KEY_RECIPE_USERNAME, userName);
        values.put(Config.KEY_RECIPE_IMAGE_PATH, imagePath);
        return db.insert(Config.TABLE_NAME, null, values);
    }
    public List<Recipe> selectAllByCategory(String category) {
        List<Recipe> recipes = new ArrayList<>();
        try (Cursor cursor = db.query(Config.TABLE_NAME,
                new String[]{Config.KEY_ID, Config.KEY_RECIPE_NAME, Config.KEY_RECIPE_INGREDIENTS, Config.KEY_RECIPE_METHOD, Config.KEY_RECIPE_CATEGORY,  Config.KEY_RECIPE_USERNAME, Config.KEY_RECIPE_IMAGE_PATH},
                Config.KEY_RECIPE_CATEGORY + " = ?",
                new String[]{category}, null, null, null))
        {
            if (cursor.moveToFirst()) {
                do {
                    Recipe recipe = new Recipe(
                            cursor.getLong(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6));
                    recipes.add(recipe);

                } while (cursor.moveToNext());
            }
        }
        return recipes;
    }

    public void update(Recipe recipe) {

        ContentValues values = new ContentValues();
        values.put(Config.KEY_RECIPE_NAME, recipe.getRecipeName());
        values.put(Config.KEY_RECIPE_INGREDIENTS, recipe.getRecipeIngredients());
        values.put(Config.KEY_RECIPE_METHOD, recipe.getRecipeMethod());
        values.put(Config.KEY_RECIPE_CATEGORY, recipe.getCategory());
        values.put(Config.KEY_RECIPE_USERNAME, recipe.getUserName());
        values.put(Config.KEY_RECIPE_IMAGE_PATH, recipe.getImagePath());
        db.update(Config.TABLE_NAME, values, Config.KEY_ID + "=" + recipe.getId(), null);
    }

    public boolean deleteById(long id) {
        return db.delete(Config.TABLE_NAME, Config.KEY_ID + "=" + id, null) > 0;
    }

    public static class Config {
        public static final String TABLE_NAME = "tbl_recipes";
        public static final String KEY_ID = "id";
        public static final String KEY_RECIPE_NAME = "recipe name";
        public static final String KEY_RECIPE_CATEGORY = "category";
        public static final String KEY_RECIPE_INGREDIENTS = "ingredients";
        public static final String KEY_RECIPE_METHOD = "recipe method";
        public static final String KEY_RECIPE_USERNAME = "recipe method";
        public static final String KEY_RECIPE_IMAGE_PATH = "imagePath";

        public static final String CREATE_TABLE_STATEMENT =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        KEY_RECIPE_NAME + " TEXT NOT NULL, " +
                        KEY_RECIPE_INGREDIENTS + " TEXT NOT NULL, " +
                        KEY_RECIPE_METHOD + " TEXT NOT NULL" +
                        KEY_RECIPE_CATEGORY + " TEXT NOT NULL, " +
                        KEY_RECIPE_USERNAME + " TEXT NOT NULL" +
                        KEY_RECIPE_IMAGE_PATH + " TEXT NOT NULL)";
    }
}
