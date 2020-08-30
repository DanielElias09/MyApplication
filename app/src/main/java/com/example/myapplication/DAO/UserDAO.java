package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.models.User;

public class UserDAO {
    SQLiteDatabase db;

    public UserDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public String insert(User user) {
        if(checkUsernameExist(user.getUsername()))
            return "Username is already exist";
        ContentValues values = new ContentValues();
        values.put(Config.KEY_USERNAME, user.getUsername());
        values.put(Config.KEY_FULLNAME, user.getFullname());
        values.put(Config.KEY_EMAIL, user.getEmail());
        values.put(Config.KEY_PASSWORD, user.getPassword());

        Long res = insert(values);
        if(res == -1)
            return "Failed";
        return "Successfully inserted";
    }

    private long insert(ContentValues values) {
        return db.insert(Config.TABLE_NAME, null,  values);
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        try (Cursor cursor = db.query(
                Config.TABLE_NAME,
                new String[]{Config.KEY_ID, Config.KEY_USERNAME, Config.KEY_FULLNAME, Config.KEY_EMAIL, Config.KEY_PASSWORD},
                Config.KEY_USERNAME + "=? AND " + Config.KEY_PASSWORD + "=?",
                new String[]{username, password}, null, null, null) ){
            return extractUserFromCursor(cursor);
        }
    }

    public boolean checkUsernameExist(String username){
        //TODO
        return false;
    }

    private User extractUserFromCursor(Cursor cursor) {
        if (cursor.moveToFirst()) {
            return new User(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );
        }

        return null;
    }

    public static class Config {
        public static final String TABLE_NAME = "users";
        public static final String KEY_ID = "id";
        public static final String KEY_USERNAME = "username";
        public static final String KEY_FULLNAME = "fullname";
        public static final String KEY_EMAIL = "email";
        public static final String KEY_PASSWORD = "password";

        public static final String CREATE_TABLE_STATEMENT =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        KEY_USERNAME + " TEXT NOT NULL, " +
                        KEY_FULLNAME + " TEXT NOT NULL, " +
                        KEY_EMAIL + " TEXT NOT NULL, " +
                        KEY_PASSWORD + " TEXT NOT NULL)";
    }
}
