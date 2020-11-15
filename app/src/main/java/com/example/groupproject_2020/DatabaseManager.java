package com.example.groupproject_2020;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "RPGenerator";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CHAR = "char";
    private static final String NAME = "name";
    private static final String RACE = "race";
    private static final String CLASS = "class";
    private static final String ALIGNMENT = "alignment";

    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlcreate = "create table "  + TABLE_CHAR + "( " + NAME;
        sqlcreate += " text primary key, " + RACE + " text, " + CLASS + "text, ";
        sqlcreate += ALIGNMENT + " text, ";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}