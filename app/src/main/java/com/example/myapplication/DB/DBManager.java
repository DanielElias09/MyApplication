package com.example.myapplication.DB;

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
        db.execSQL("CREATE TABLE tbl_comments (id integer primary key autoincrement, username recipeId, fullname username, comment text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_users");
        db.execSQL("DROP TABLE IF EXISTS tbl_recipes");
        db.execSQL("DROP TABLE IF EXISTS tbl_comments");
        onCreate(db);
    }
}
