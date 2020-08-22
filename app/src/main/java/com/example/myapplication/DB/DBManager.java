package com.example.myapplication.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBManager extends SQLiteOpenHelper {
    public DBManager(@Nullable Context context) {
        super(context, "Foodly.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tbl_users (id integer primary key autoincrement, username text, fullname text, email text, password text)");
        db.execSQL("CREATE TABLE tbl_recipes (id integer primary key autoincrement, name text, ingredients text, method text, category text)");
        db.execSQL("CREATE TABLE tbl_comments (id integer primary key autoincrement, recipeId text, username text, comment text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_users");
        db.execSQL("DROP TABLE IF EXISTS tbl_recipes");
        db.execSQL("DROP TABLE IF EXISTS tbl_comments");
        onCreate(db);
    }

    public String addUser(String username, String fullname, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("fullname", fullname);
        cv.put("email", email);
        cv.put("password", password);

        long res = db.insert("tbl_users", null, cv);
        if(res==-1)
            return "Failed";
        else
            return "Successfully inserted";
    }
    public String addRecipe(String name, String ingredients, String method, String category){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("ingredients", ingredients);
        cv.put("method", method);
        cv.put("category", category);

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
    }
}
