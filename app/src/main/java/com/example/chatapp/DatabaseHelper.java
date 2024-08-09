package com.example.chatapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "chatApp.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USERS = "users";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_USERS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL UNIQUE," +
                "password TEXT NOT NULL" +
                ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean insertUser(String username, String hashedPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", hashedPassword);
        long result = db.insert(TABLE_USERS, null, contentValues);
        return result != -1;
    }

    public String getHashedPassword(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{"password"}, "username=?", new String[]{username}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            String hashedPassword = cursor.getString(0);
            cursor.close();
            return hashedPassword;
        }
        return null;
    }
}
