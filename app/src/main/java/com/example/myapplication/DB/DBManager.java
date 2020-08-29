package com.example.myapplication.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.myapplication.DAO.RecipeDAO;
import com.example.myapplication.DAO.UserDAO;

import java.io.File;

public class DBManager extends SQLiteOpenHelper {


    public DBManager(Context context, String databaseName, int databaseVersion) {
        super(context, databaseName, null, databaseVersion);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDAO.Config.CREATE_TABLE_STATEMENT);
        db.execSQL(RecipeDAO.Config.CREATE_TABLE_STATEMENT);
        //db.execSQL(CommentDAO.Config.CREATE_TABLE_STATEMENT);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS  "+RecipeDAO.Config.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS  "+RecipeDAO.Config.TABLE_NAME);
        //db.execSQL("DROP TABLE IF EXISTS tbl_comments");
        onCreate(db);
    }

    /* public String addUser(String username, String fullname, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("fullname", fullname);
        cv.put("email", email);
        cv.put("password", password);

        if(checkUsernameExist(username)) return "Username is already exists";
        long res = db.insert("tbl_users", null, cv);
        if(res==-1)
            return "Failed";
        else
            return "Successfully inserted";
    }

    public boolean checkUser(String username, String password){
        String[] columns = {"id"};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "username=? and password=?";
        String[] selectionArgs={username, password};
        Cursor cursor = db.query("tbl_users", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        if(count>0)
            return true;
        else
            return false;
    }

    public boolean checkUsernameExist(String username){
        String[] columns = {"username"};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "username=?";
        String[] selectionArgs={username};
        Cursor cursor = db.query("tbl_users", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count==0)
            return false;
        return true;
    }

    public String addRecipe(String name, String ingredients, String method, String category, String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("ingredients", ingredients);
        cv.put("method", method);
        cv.put("category", category);
        cv.put("username", username);

        long res = db.insert("tbl_recipes", null, cv);
        if(res==-1)
            return "Failed";
        return "Successfully inserted";
    }
    public String addComment(String recipeId, String username, String comment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("recipeId", recipeId);
        cv.put("username", username);
        cv.put("comment", comment);

        long res = db.insert("tbl_comments", null, cv);
        if(res==-1)
            return "Failed";
        return "Successfully inserted";
    }*/
}
