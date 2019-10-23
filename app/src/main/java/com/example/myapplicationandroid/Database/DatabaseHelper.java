package com.example.myapplicationandroid.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(@Nullable Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table user(email text primary key, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists user");
    }
    //inserting in database
    public boolean insert(String email, String password){
        SQLiteDatabase sql = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long insert = sql.insert("user", null, contentValues);
        if (insert == -1)
            return false;
        else
            return true;
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from user where email=?", new String[]{email} );
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }

    public Boolean emailPassword(String email, String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from user where email=? and password=?", new String[]{email, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    //method to view data
    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from user";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }
}
