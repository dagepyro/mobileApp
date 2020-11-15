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
    public void onCreate(SQLiteDatabase db) {
        String sqlcreate = "create table "  + TABLE_CHAR + "( " + NAME;
        sqlcreate += " text primary key, " + RACE + " text, " + CLASS + "text, ";
        sqlcreate += ALIGNMENT + " text )";

        db.execSQL(sqlcreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL( "drop table if exists " + TABLE_CHAR );
        onCreate( db );
    }

    public void insertChar(character newChar){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_CHAR + " values ('" + newChar.getName() + "', '"
                + newChar.getRace() + "', '" + newChar.getCharclass() + "', '" + newChar.getAlignment() + "')";

        db.execSQL(sqlInsert);
        db.close();
    }
}