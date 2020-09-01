package com.example.myapplication.DAO;

import android.arch.persistence.room.Dao;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.models.Recipe;

import java.util.ArrayList;
import java.util.List;
@Dao
public class RecipeDAO {
    
    SQLiteDatabase db;

    public RecipeDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public String insert(Recipe recipe) {
        ContentValues values = new ContentValues();
        values.put(Config.KEY_RECIPE_NAME, recipe.getRecipeName());
        values.put(Config.KEY_RECIPE_INGREDIENTS, recipe.getRecipeIngredients());
        values.put(Config.KEY_RECIPE_METHOD, recipe.getRecipeMethod());
        values.put(Config.KEY_RECIPE_CATEGORY, recipe.getCategory());
        values.put(Config.KEY_RECIPE_USERNAME, recipe.getUserName());
        values.put(Config.KEY_RECIPE_IMAGE_PATH, recipe.getImagePath());
        Long res = insert(values);
        if(res == -1)
            return "Failed";
        return "Successfully inserted";
    }

    private long insert(ContentValues values) {
        return db.insert(Config.TABLE_NAME, null,  values);
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

    public Recipe getById(long id) {
        Cursor cursor = db.rawQuery("select * from " + Config.TABLE_NAME +" where "+Config.KEY_ID+" = "+id, null);
        return extractRecipeFromCursor(cursor);

    }
    private Recipe extractRecipeFromCursor(Cursor cursor) {
        if (cursor.moveToFirst()) {
            return new Recipe(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
        }
        return null;
    }

    public List<Recipe> selectAllByUsername(String username) {
        List<Recipe> recipes = new ArrayList<>();
        try (Cursor cursor = db.query(Config.TABLE_NAME,
                new String[]{Config.KEY_ID, Config.KEY_RECIPE_NAME, Config.KEY_RECIPE_INGREDIENTS, Config.KEY_RECIPE_METHOD, Config.KEY_RECIPE_CATEGORY,  Config.KEY_RECIPE_USERNAME, Config.KEY_RECIPE_IMAGE_PATH},
                Config.KEY_RECIPE_USERNAME + " = ?",
                new String[]{username}, null, null, null))
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

    public static class Config {
        public static final String TABLE_NAME = "recipes";
        public static final String KEY_ID = "id";
        public static final String KEY_RECIPE_NAME = "recipeName";
        public static final String KEY_RECIPE_CATEGORY = "category";
        public static final String KEY_RECIPE_INGREDIENTS = "ingredients";
        public static final String KEY_RECIPE_METHOD = "recipeMethod";
        public static final String KEY_RECIPE_USERNAME = "username";
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
