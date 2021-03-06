package com.example.myapplication.adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.DAO.RecipeDAO;
import com.example.myapplication.DAO.UserDAO;
import com.example.myapplication.DB.DBManager;
import com.example.myapplication.models.Recipe;
import com.example.myapplication.models.User;
import com.example.myapplication.utils.UserPreferences;

import java.util.List;

public class DatabaseAdapter {

    private static DatabaseAdapter instance;
    private static final String DATABASE_NAME = "MyDB.db";
    private static final int DATABASE_VERSION = 1;

    private DBManager dbManager;
    private SQLiteDatabase db;
    private Context mContext;

    private UserDAO userDAO;
    private RecipeDAO recipeDAO;

    public static DatabaseAdapter getInstance(Context context) {
        if (instance == null) {
            synchronized (DatabaseAdapter.class) {
                if (instance == null)
                    instance = new DatabaseAdapter(context).open();
            }
        }

        return instance;
    }

    private DatabaseAdapter(Context context) {
        mContext = context;
        dbManager = new DBManager(context, DATABASE_NAME, DATABASE_VERSION);
    }

    private DatabaseAdapter open() {
        db = dbManager.getWritableDatabase();
        userDAO = new UserDAO(db);
        recipeDAO = new RecipeDAO(db);
        return this;
    }

    public boolean logIn(String username, String password) {
        User currentUser = userDAO.getUserByUsernameAndPassword(username, password);
        UserPreferences.saveCurrentUser(mContext, currentUser);
        return currentUser != null;
    }

    public String addNewUser(User user) {
        return userDAO.insert(user);
    }

    public Long addNewRecipe(Recipe recipe) {
        return recipeDAO.insert(recipe);
    }

    public Recipe getRecipeById(Long id){return recipeDAO.getById(id);}

    public void updateRecipe(Recipe recipe) {
        recipeDAO.update(recipe);
    }

    public void deleteRecipe(long recipeId) {
        recipeDAO.deleteById(recipeId);
    }

    public List<Recipe> getAllRecipesByCategory(String category) {
        return recipeDAO.selectAllByCategory(category);
    }

    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    public List<Recipe> getAllRecipesByUsername(String username) {
        return recipeDAO.selectAllByUsername(username);
    }

    public String updateUser(User user, String username) {
        return userDAO.update(user, username);
    }
}
