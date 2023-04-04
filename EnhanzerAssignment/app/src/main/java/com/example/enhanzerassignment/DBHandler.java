package com.example.enhanzerassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(Context context) {
        super(context, "MyAssignment",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQL = "CREATE TABLE customer (" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT," +
                "address TEXT," +
                "state TEXT)" ;

        sqLiteDatabase.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long addCustomer(String name, String address, String state){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("address",address);
        contentValues.put("state",state);

        long newID = db.insert("customer",null,contentValues);
        return newID;
    }


    public Cursor readAllInfo(){
        SQLiteDatabase db = getReadableDatabase();
        String [] projection = {
                "id",
                "name",
                "address"
        };

        Cursor cursor = db.query(
                "customer",
                projection,
                null,
                null,
                null,
                null,
                null
        );

        return cursor;
    }

}