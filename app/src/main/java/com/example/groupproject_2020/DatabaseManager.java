package com.example.groupproject_2020;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "RPGenerator";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CHARACTER = "char";
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
    private static final String WEAPON_TYPE = "weaponType";
    private static final String ARMOR_TYPE = "armorType";
    private static final String STRENGTH_REQUIREMENT = "strengthRequirement";
    private static final String CREATE_CHARACTER_TABLE = "create table " + TABLE_CHARACTER +"( "+ID+" TEXT PRIMARY KEY, "+ NAME +" text, " + ALIGNMENT+" text, "+ RACE +" text, "+ CLASS  + " text)";
    private static final String CREATE_MONSTER_TABLE = "create table " +TABLE_MONSTER+"("+ID+" TEXT PRIMARY KEY, "+NAME+" TEXT, "+ ARMOR_CLASS+" TEXT, "+ HIT_POINTS+" TEXT, "+EXPERIENCE+" TEXT)";
    private static final String CREATE_STATS_TABLE = "CREATE TABLE "+TABLE_STATS+"("+ID+" TEXT, "+NAME+" TEXT, "+STRENGTH+" TEXT, "+ DEXTERITY+" TEXT, "+ CONSTITUTION+" TEXT, "+INTELLIGENCE+" TEXT, "+ WISDOM+" TEXT, "+CHARISMA+" TEXT)";
    private static final String CREATE_WEAPONS_TABLE = "CREATE TABLE "+TABLE_WEAPONS+"("+ID+" TEXT PRIMARY KEY, "+ WEAPON_TYPE+" TEXT, "+NAME+" TEXT, "+DAMAGE+" TEXT, "+DAMAGE_TYPE+" TEXT,"+ TRAITS+" TEXT, "+PROPERTY+" TEXT)";
    private static final String CREATE_ARMOR_TABLE = "CREATE TABLE "+TABLE_ARMOR+"("+ID+" TEXT PRIMARY KEY, "+ARMOR_TYPE+" TEXT, "+ NAME+" TEXT, "+ARMOR_CLASS+" TEXT, "+ STRENGTH_REQUIREMENT+" TEXT, "+ TRAITS+" TEXT, "+ PROPERTY+" TEXT)";


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
        db.execSQL( "drop table if exists " + TABLE_CHARACTER);
        db.execSQL( "drop table if exists " + TABLE_MONSTER);
        db.execSQL( "drop table if exists " + TABLE_STATS);
        db.execSQL( "drop table if exists " + TABLE_WEAPONS);
        db.execSQL( "drop table if exists " + TABLE_ARMOR);
        onCreate( db );
    }

    public String getNewCharacterID(){
        String sqlQuery = "select * from "+ TABLE_CHARACTER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery,null);
        Log.w("Character", "new Character ID is C"+cursor.getCount());
        return "C"+cursor.getCount();
    }
    public String getNewMonsterID(){
        String sqlQuery = "select * from "+TABLE_MONSTER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery,null);
        return "M"+cursor.getCount();
    }
    public String getNewWeaponID(){
        String sqlQuery = "select * from "+TABLE_WEAPONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery,null);
        return "W"+cursor.getCount();
    }
    public String getNewArmorID(){
        String sqlQuery = "select * from "+TABLE_ARMOR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery,null);
        return "A"+cursor.getCount();
    }

    public ArrayList<character> selectAllCharacters(){
        String sqlQuery = "select * from " + TABLE_CHARACTER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        ArrayList<character> characters = new ArrayList<>();
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            character currentCharacter = new character(Integer.parseInt(id.substring(1)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            characters.add(currentCharacter);
        }
        db.close();
        return characters;
    }

    public ArrayList<monster> selectAllMonsters(){
        String sqlQuery = "select * from " + TABLE_MONSTER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        ArrayList<monster> monsters = new ArrayList<>();
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            Log.w("DEBUG",cursor.getString(2));
            monster currentMonster = new monster(
                    Integer.parseInt(id.substring(1)),
                    cursor.getString(1),
                    Integer.parseInt(cursor.getString(2)),
                    Integer.parseInt(cursor.getString(3)),
                    Integer.parseInt(cursor.getString(4)));
            monsters.add(currentMonster);
        }
        db.close();
        return monsters;
    }

    public ArrayList<weapon> selectAllWeapons(){
        String sqlQuery = "select * from "+TABLE_WEAPONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        ArrayList<weapon> weapons = new ArrayList<>();
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            id = id.substring(1);
            weapon currentWeapon = new weapon(Integer.parseInt(id), Integer.parseInt(cursor.getString(1)), cursor.getString(2),cursor.getString(3),cursor.getString(4), cursor.getString(5), cursor.getString(6));
            weapons.add(currentWeapon);
        }
        return weapons;

    }

    public ArrayList<armor> selectAllArmor(){
        String sqlQuery = "select * from "+TABLE_ARMOR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery,null);
        ArrayList<armor> armors = new ArrayList<>();
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            id = id.substring(1);
            armor currentArmor = new armor(Integer.parseInt(id),Integer.parseInt(cursor.getString(4)), cursor.getString(2),cursor.getString(3), cursor.getString(5),cursor.getString(6),cursor.getString(1));
            armors.add(currentArmor);
        }
        return armors;
    }

    public void insertChar(character newChar){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_CHARACTER + " values ('"+ getNewCharacterID()+"', '" + newChar.getName() + "', '"
                + newChar.getAlignment() + "', '" + newChar.getRace() + "', '" + newChar.getCharclass() + "')";
        db.execSQL(sqlInsert);
        db.close();
        insertCharacterStats(newChar);
    }

    public void deleteCharacterByID(int charID){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from "+ TABLE_CHARACTER +" where "+ ID +" = 'C"+charID+"'";
        db.execSQL(sqlDelete);
        db.close();
    }

    public void updateCharacter(character updateCharacter){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqUpdate = "update "+ TABLE_CHARACTER +" set "+NAME+" = '"+ updateCharacter.getName() + "' , " + ALIGNMENT +" = '"+updateCharacter.getAlignment()+"', "+CLASS+" = '"+updateCharacter.getCharclass()+"' , "+RACE+" = '"+updateCharacter.getRace()+"' where " + ID + " = 'C"+updateCharacter.getId()+"'";
        db.execSQL(sqUpdate);
        db.close();
    }

    public void insertMonster(monster newMonster){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_MONSTER + " values ('"+ getNewMonsterID()+"', '" + newMonster.getName() + "', '"
                + newMonster.getArmorclass() + "', '" + newMonster.getHitpoints() + "', '" + newMonster.getExp() + "')";
        db.execSQL(sqlInsert);
        db.close();
    }

    public void deleteMonsterById(int monster){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from "+TABLE_MONSTER+" where "+ ID +" = 'M"+ monster+"'";
        db.execSQL(sqlDelete);
        db.close();
    }

    public void updateMonster(monster updateMonster){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqUpdate = "update "+ TABLE_MONSTER +" set "+NAME+" = '"+ updateMonster.getName() + "' , " + ARMOR_CLASS +" = '"+updateMonster.getArmorclass()+"', "+HIT_POINTS+" = '"+updateMonster.getHitpoints()+"' , "+EXPERIENCE+" = '"+updateMonster.getExp()+"' where '"+ID+"' = 'M"+updateMonster.getId();
        db.execSQL(sqUpdate);
        db.close();
    }

    public void insertCharacterStats(character newCharacter){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_STATS + " values ('"+newCharacter.getId()+"', '" + newCharacter.getName() + "', '"
                + newCharacter.stats.getStrength() + "', '" + newCharacter.stats.getDexterity() + "', '" + newCharacter.stats.getConstitution() +"', '"+newCharacter.stats.getIntelligence()+"', '"+newCharacter.stats.getWisdom()+"', '"+newCharacter.stats.getCharisma()+ "')";
        db.execSQL(sqlInsert);
        db.close();
    }

    public void updateStatsByCharacterID(character updateCharacter){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlUpdate = "update "+TABLE_STATS+" set "+STRENGTH+" = "+updateCharacter.stats.getStrength()+", "+DEXTERITY+" = "+updateCharacter.stats.getDexterity()+", "+CONSTITUTION+" = "+updateCharacter.stats.getConstitution()+", "+INTELLIGENCE+" = "+updateCharacter.stats.getIntelligence()+", "+WISDOM+" = "+updateCharacter.stats.getWisdom()+", "+CHARISMA+" = "+updateCharacter.stats.getCharisma()+" where "+NAME+" = 'C"+updateCharacter.getName()+"'";
        db.execSQL(sqlUpdate);
        db.close();
    }

    public void deleteStatsByCharacterID(character deleteCharacter){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from "+TABLE_STATS+" where "+ ID +" = 'C"+ deleteCharacter.getId()+"'";
        db.execSQL(sqlDelete);
        db.close();
    }

    public void insertMonsterStats(monster newMonster){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_CHARACTER + " values ('"+newMonster.getId()+"', '" + newMonster.getName() + "', '"
                + newMonster.stats.getStrength() + "', '" + newMonster.stats.getDexterity() + "', '" + newMonster.stats.getConstitution() +"', '"+newMonster.stats.getIntelligence()+"', '"+newMonster.stats.getWisdom()+"', '"+newMonster.stats.getCharisma()+ "')";
        db.execSQL(sqlInsert);
        db.close();
    }

    public void updateStatsByMonsterID(monster updateMonster){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlUpdate = "update "+TABLE_STATS+" set "+STRENGTH+" = "+updateMonster.stats.getStrength()+", "+DEXTERITY+" = "+updateMonster.stats.getDexterity()+", "+CONSTITUTION+" = "+updateMonster.stats.getConstitution()+", "+INTELLIGENCE+" = "+updateMonster.stats.getIntelligence()+", "+WISDOM+" = "+updateMonster.stats.getWisdom()+", "+CHARISMA+" = "+updateMonster.stats.getCharisma()+" where "+ID+" = 'M"+updateMonster.getId()+"'";
        db.execSQL(sqlUpdate);
        db.close();
    }

    public void deleteStatsByCharacterID(monster deleteMonster){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from "+TABLE_STATS+" where "+ ID +" = 'C"+ deleteMonster.getId()+"'";
        db.execSQL(sqlDelete);
        db.close();
    }

    public void insertWeapon(weapon newWeapon){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_WEAPONS + " values ('"+getNewWeaponID()+"', '" + newWeapon.getWeaponType() + "', '"
                + newWeapon.getName() + "', '" + newWeapon.getDamage() + "', '" + newWeapon.getDamageType() +"', '"+newWeapon.getTraits()+"', '"+newWeapon.getProperty()+ "')";
        db.execSQL(sqlInsert);
        db.close();
    }

    public void  updateWeapon(weapon weapon){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlupdate = "update "+TABLE_WEAPONS+" set "+WEAPON_TYPE+" = '"+ weapon.getWeaponType() +"', "+NAME+" = '"+weapon.getName()+"', "+DAMAGE+" = '"+weapon.getDamage()+"', "+DAMAGE_TYPE+" = '"+weapon.getDamageType()+"', "+TRAITS+" = '"+weapon.getTraits()+"', "+PROPERTY+" = '"+weapon.getProperty()+"' where "+ID+" = 'W"+weapon.getId()+"'";
        db.execSQL(sqlupdate);
        db.close();
    }

    private void deleteWeaponByID(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from "+TABLE_WEAPONS+" where "+ID+" = 'W"+id+"'" ;
        db.execSQL(sqlDelete);
        db.close();
    }

    private void insertArmor(armor armor){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into "+TABLE_ARMOR+" values ('"+getNewArmorID()+"', '"+armor.getType()+"', '"+armor.getName()+"', '"+armor.getArmorClass()+"', '"+armor.getStrength()+"', '"+armor.getTraits()+"', '"+armor.getProperty()+"'";
        db.execSQL(sqlInsert);
        db.close();
    }

    private void updateArmor(armor armor){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlUpdate = "update "+TABLE_ARMOR+" set "+NAME+" = '"+ armor.getName()+"', "+ARMOR_CLASS+" = '"+armor.getArmorClass()+"', "+STRENGTH_REQUIREMENT+" = '"+armor.getStrength()+"', "+TRAITS+" = '"+armor.getTraits()+"', "+PROPERTY+" = '"+armor.getProperty()+"' where "+ID+" = 'A"+armor.getId()+"'";
        db.execSQL(sqlUpdate);
        db.close();
    }

    private void deleteArmorByID(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from "+TABLE_ARMOR+" where "+ID+" = 'A"+id+"'";
        db.execSQL(sqlDelete);
    }
}
