package com.example.abandon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.service.autofill.UserData;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, "Entry.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Entry(id INTEGER PRIMARY KEY AUTOINCREMENT, activ TEXT, mood TEXT, date TEXT, time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertUserData(String activ, String mood, String date, String time)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("activ", activ);
        contentValues.put("mood", mood);
        contentValues.put("date", date);
        contentValues.put("time", time);
        long result = DB.insertOrThrow("Entry", null, contentValues);
        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Boolean updateUserData(String activ, String mood)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("activ", activ);
        contentValues.put("mood", mood);
        Cursor cursor = DB.rawQuery("Select * from UserData where activ = ?", new String[]{activ});
        if(cursor.getCount() == 0)
        {
            return false;
        }
        long result = DB.update("UserData", contentValues, "activ=?", new String[] {activ});
        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Boolean deleteUserData(String activ)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserData where activ = ?", new String[]{activ});
        if(cursor.getCount() == 0)
        {
            return false;
        }
        long result = DB.delete("UserData" , "activ=?", new String[] {activ});
        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getItemID(String activ)
    {
        SQLiteDatabase db = this. getWritableDatabase();
        String query = "SELECT " + "ID" + " FROM " + "DB" + " WHERE " + "activ" + "mood" + " = " + activ + "*";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public ArrayList<entry> getDateEntries(String d) {
        ArrayList<entry> dude = new ArrayList<entry>();
        Cursor cursor = getData();
        while(cursor.moveToNext())
        {
            int dateInd = cursor.getColumnIndex("date");
            String date = cursor.getString(dateInd);
            if(date.equals(d)) {


                int idInd = cursor.getColumnIndex("id");
                int timeInd = cursor.getColumnIndex("time");
                int moodInd = cursor.getColumnIndex("mood");
                int activInd = cursor.getColumnIndex("activ");

                int id = cursor.getInt(idInd);
                String time = cursor.getString(timeInd);
                String mood = cursor.getString(moodInd);
                String activ = cursor.getString(activInd);
                entry en = new entry(activ, mood, date, time, String.valueOf(id));
                dude.add(en);
            }
        }
        return dude;
    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Entry", null);
        return cursor;
    }
}
