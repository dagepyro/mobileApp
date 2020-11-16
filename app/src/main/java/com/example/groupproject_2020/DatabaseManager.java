package com.example.groupproject_2020;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "RPGenerator";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CHAR = "char";
    private static final String NAME = "name";
    private static final String RACE = "race";
    private static final String CLASS = "class";
    private static final String ALIGNMENT = "alignment";
    private static final String ID = "ID";
    private static final String CREATE_CHARACTER_TABLE = "create table" + TABLE_CHAR +"( "+ID+" text primary key, "+ NAME +" text, " + RACE+" text, "+ CLASS +" text, "+ ALIGNMENT  + " text)";


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

    public ArrayList<character> selectAllCharacters(){
        String sqlQuery = "select * from " + TABLE_CHAR;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<character> characters = new ArrayList<>();

        while (cursor.moveToNext()){
            character currentCharacter = new character(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            characters.add(currentCharacter);
        }
        db.close();
        return characters;
    }

    public void insertChar(character newChar){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_CHAR + " values ('" + newChar.getName() + "', '"
                + newChar.getRace() + "', '" + newChar.getCharclass() + "', '" + newChar.getAlignment() + "')";
        db.execSQL(sqlInsert);
        db.close();
    }

    public void deleteCharacterByName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from "+TABLE_CHAR+" where "+ NAME +" = "+ name;

        db.execSQL(sqlDelete);
        db.close();
    }

    public void updateCharacterByName(String name, String alignment, String charclass, String race){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqUpdate = "update "+TABLE_CHAR+" set "+NAME+" = '"+ name + "' , " + ALIGNMENT +" = '"+alignment+"', "+CLASS+" = '"+charclass+"' , "+RACE+" = '"+race;

        db.execSQL(sqUpdate);
        db.close();
    }

}