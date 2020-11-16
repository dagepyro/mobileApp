package com.example.groupproject_2020;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PrimitiveIterator;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "RPGenerator";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CHAR = "char";
    private static final String TABLE_MONSTER = "monster";
    private static final String TABLE_STATS = "stats";
    private static final String TABLE_WEAPONS = "weapons";
    private static final String TABLE_ARMOR = "armor";
    private static final String NAME = "name";
    private static final String RACE = "race";
    private static final String CLASS = "class";
    private static final String ALIGNMENT = "alignment";
    private static final String ID = "ID";
    private static final String ARMOR_CLASS = "armorClass";
    private static final String HIT_POINTS = "hitPoints";
    private static final String EXPERIENCE = "experience";
    private static final String STRENGTH = "strength";
    private static final String DEXTERITY = "dexterity";
    private static final String CONSTITUTION = "constitution";
    private static final String INTELLIGENCE = "intelligence";
    private static final String WISDOM = "wisdom";
    private static final String CHARISMA = "charisma";
    private static final String DAMAGE = "damage";
    private static final String DAMAGE_TYPE = "damageType";
    private static final String TRAITS = "traits";
    private static final String PROPERTY = "property";
    private static final String STRENGTH_REQUIREMENT = "strengthRequirement";
    private static final String CREATE_CHARACTER_TABLE = "create table " + TABLE_CHAR +"( "+ NAME +" text PRIMARY KEY, " + RACE+" text, "+ CLASS +" text, "+ ALIGNMENT  + " text)";
    private static final String CREATE_MONSTER_TABLE = "create table " +TABLE_MONSTER+"("+NAME+" TEXT PRIMARY KEY, "+ ARMOR_CLASS+" TEXT, "+ HIT_POINTS+" TEXT, "+EXPERIENCE+" TEXT)";
    private static final String CREATE_STATS_TABLE = "CREATE TABLE "+TABLE_STATS+"("+NAME+" TEXT PRIMARY KEY, "+STRENGTH+" TEXT, "+ DEXTERITY+" TEXT, "+ CONSTITUTION+" TEXT, "+INTELLIGENCE+" TEXT, "+ WISDOM+" TEXT, "+CHARISMA+" TEXT)";
    private static final String CREATE_WEAPONS_TABLE = "CREATE TABLE "+TABLE_WEAPONS+"("+NAME+" TEXT PRIMARY KEY, "+DAMAGE+" TEXT, "+DAMAGE_TYPE+" TEXT,"+ TRAITS+" TEXT, "+PROPERTY+" TEXT)";
    private static final String CREATE_ARMOR_TABLE = "CREATE TABLE "+TABLE_ARMOR+"("+ NAME+" TEXT PRIMARY KEY, "+ARMOR_CLASS+" TEXT, "+ STRENGTH_REQUIREMENT+" TEXT, "+ TRAITS+" TEXT, "+ PROPERTY+" TEXT)";


    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CHARACTER_TABLE);
        db.execSQL(CREATE_MONSTER_TABLE);
        db.execSQL(CREATE_STATS_TABLE);
        db.execSQL(CREATE_WEAPONS_TABLE);
        db.execSQL(CREATE_ARMOR_TABLE);
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
HashMap hashMap = new HashMap();
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