package com.example.groupproject_2020;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

import org.w3c.dom.ls.LSOutput;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "RPGenerator";
    private static final int DATABASE_VERSION = 2;
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
    private static final String IMAGE = "image";
    private static final String IMAGE_NAME = "imageName";
    private static final String CREATE_CHARACTER_TABLE = "create table " + TABLE_CHARACTER +"( "+ID+" TEXT PRIMARY KEY, "+ NAME +" text, " + RACE+" text, "+ CLASS +" text, "+ ALIGNMENT  + " text, "+IMAGE+" blob, "+IMAGE_NAME+" text)";
    private static final String CREATE_MONSTER_TABLE = "create table " +TABLE_MONSTER+"("+ID+" TEXT PRIMARY KEY, "+NAME+" TEXT, "+ ARMOR_CLASS+" TEXT, "+ HIT_POINTS+" TEXT, "+EXPERIENCE+" TEXT,"+IMAGE+" blob, "+IMAGE_NAME+" text )";
    private static final String CREATE_STATS_TABLE = "CREATE TABLE "+TABLE_STATS+"("+ID+" TEXT, "+NAME+" TEXT, "+STRENGTH+" TEXT, "+ DEXTERITY+" TEXT, "+ CONSTITUTION+" TEXT, "+INTELLIGENCE+" TEXT, "+ WISDOM+" TEXT, "+CHARISMA+" TEXT)";
    private static final String CREATE_WEAPONS_TABLE = "CREATE TABLE "+TABLE_WEAPONS+"("+ID+" TEXT PRIMARY KEY, "+ WEAPON_TYPE+" TEXT, "+NAME+" TEXT, "+DAMAGE+" TEXT, "+DAMAGE_TYPE+" TEXT,"+ TRAITS+" TEXT, "+PROPERTY+" TEXT, "+IMAGE+" blob,"+IMAGE_NAME+" text)";
    private static final String CREATE_ARMOR_TABLE = "CREATE TABLE "+TABLE_ARMOR+"("+ID+" TEXT PRIMARY KEY, "+ARMOR_TYPE+" TEXT, "+ NAME+" TEXT, "+ARMOR_CLASS+" TEXT, "+ STRENGTH_REQUIREMENT+" TEXT, "+ TRAITS+" TEXT, "+ PROPERTY+" TEXT, "+IMAGE+" blob, "+IMAGE_NAME+" text)";


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
        int idNumber = 0;
        cursor.moveToLast();
        if (cursor.getCount() < 0)
            idNumber = Integer.parseInt(cursor.getString(0).substring(1))+1;
        Log.w("Character", "new Character ID is C "+idNumber);
        return "C"+idNumber;
    }
    public String getNewMonsterID(){
        String sqlQuery = "select * from "+TABLE_MONSTER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery,null);
        int idNumber = 0;
        cursor.moveToLast();
        if (cursor.getCount() < 0)
        idNumber = Integer.parseInt(cursor.getString(0).substring(1))+1;
        Log.w("Monster", "new Monster ID is M"+idNumber);
        return "M"+idNumber;
    }
    public String getNewWeaponID(){
        String sqlQuery = "select * from "+TABLE_WEAPONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery,null);
        int idNumber = 0;
        if (cursor.getCount()<0)
        cursor.moveToLast();
        idNumber = Integer.parseInt(cursor.getString(0).substring(1))+1;
        Log.w("Weapon", "new Weapon ID is W"+idNumber);
        return "W"+idNumber;
    }
    public String getNewArmorID(){
        String sqlQuery = "select * from "+TABLE_ARMOR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery,null);
        int idNumber = 0;
        if (cursor.getCount()<0)
        cursor.moveToLast();
        idNumber = Integer.parseInt(cursor.getString(0).substring(1))+1;
        Log.w("Armor", "new Armor ID is A "+idNumber);
        return "A"+idNumber;
    }

    public ArrayList<character> selectAllCharacters(){
        String sqlQuery = "select * from " + TABLE_CHARACTER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        ArrayList<character> characters = new ArrayList<>();
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            byte[] bmArray = cursor.getBlob(5);
            Bitmap image = BitmapFactory.decodeByteArray(bmArray,0,bmArray.length);
            character currentCharacter = new character(Integer.parseInt(id.substring(1)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    image,
                    cursor.getString(6));
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
            byte[] bmArray = cursor.getBlob(5);
            Bitmap image = BitmapFactory.decodeByteArray(bmArray,0,bmArray.length);
            monster currentMonster = new monster(
                    Integer.parseInt(id.substring(1)),
                    cursor.getString(1),
                    Integer.parseInt(cursor.getString(2)),
                    Integer.parseInt(cursor.getString(3)),
                    Integer.parseInt(cursor.getString(4)),
                    image,
                    cursor.getString(6));
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
            byte[] bmArray = cursor.getBlob(7);
            Bitmap image = BitmapFactory.decodeByteArray(bmArray,0,bmArray.length);
            weapon currentWeapon = new weapon(Integer.parseInt(id),
                    cursor.getString(1),
                    Integer.parseInt(cursor.getString(2)),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    image,
                    cursor.getString(8));
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
            byte[] bmArray = cursor.getBlob(7);
            Bitmap image = BitmapFactory.decodeByteArray(bmArray,0,bmArray.length);
            armor currentArmor = new armor(Integer.parseInt(id),
                    cursor.getString(1),
                    Integer.parseInt(cursor.getString(2)),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    image,
                    cursor.getString(8));
            armors.add(currentArmor);
        }
        return armors;
    }

    public void insertChar(character newChar){
        SQLiteDatabase db = this.getWritableDatabase();
        newChar.setId(Integer.parseInt(getNewCharacterID().substring(1)));
        Bitmap image = newChar.getImage();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        ContentValues cv = new ContentValues();
        cv.put("image",stream.toByteArray());
        String sqlInsert = "insert into " + TABLE_CHARACTER
                + " values ('C"+ newChar.getId()+"', '"
                + newChar.getName() + "', '"
                + newChar.getRace() + "', '"
                + newChar.getCharclass() + "', '"
                + newChar.getAlignment() + "', '"
                +cv+"', '"
                +newChar.getImageName()+"')";
        db.execSQL(sqlInsert);
        db.close();
        insertCharacterStats(newChar);
    }

    public void deleteCharacter(character delchar){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from "+ TABLE_CHARACTER +" where "+ ID +" = 'C"+delchar.getId()+"'";
        db.execSQL(sqlDelete);
        db.close();
        deleteStatsByCharacterID(delchar);
    }

    public void updateCharacter(character updateCharacter){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqUpdate = "update "+ TABLE_CHARACTER
                +" set "+NAME+" = '"+ updateCharacter.getName() + "' , "
                + ALIGNMENT +" = '"+updateCharacter.getAlignment()+"', "
                +CLASS+" = '"+updateCharacter.getCharclass()+"' , "
                +RACE+" = '"+updateCharacter.getRace()
                +"' where " + ID + " = 'C"+updateCharacter.getId()+"'";
        db.execSQL(sqUpdate);
        db.close();
    }

    public void insertMonster(monster newMonster){
        SQLiteDatabase db = this.getWritableDatabase();
        newMonster.setId(Integer.parseInt(getNewMonsterID().substring(1)));
        Bitmap image = newMonster.getImage();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        ContentValues cv = new ContentValues();
        cv.put("image",stream.toByteArray());
        String sqlInsert = "insert into " + TABLE_MONSTER
                + " values ('M"+ newMonster.getId()+"', '"
                + newMonster.getName() + "', '"
                + newMonster.getArmorclass() + "', '"
                + newMonster.getHitpoints() + "', '"
                + newMonster.getExp() + "', '"
                +cv+"', '"
                +newMonster.getImageName()+"')";
        db.execSQL(sqlInsert);
        db.close();
    }

    public void deleteMonster(monster monster){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from "+TABLE_MONSTER+" where "+ ID +" = 'M"+ monster+"'";
        db.execSQL(sqlDelete);
        db.close();
    }

    public void updateMonster(monster updateMonster){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqUpdate = "update "+ TABLE_MONSTER
                +" set "+NAME+" = '"+ updateMonster.getName() + "' , "
                + ARMOR_CLASS +" = '"+updateMonster.getArmorclass()+"', "
                +HIT_POINTS+" = '"+updateMonster.getHitpoints()+"' , "
                +EXPERIENCE+" = '"+updateMonster.getExp()
                +"' where '"+ID+"' = 'M"+updateMonster.getId();
        db.execSQL(sqUpdate);
        db.close();
    }

    public void insertCharacterStats(character newCharacter){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_STATS
                + " values ('C"+newCharacter.getId()+"', '"
                + newCharacter.getName() + "', '"
                + newCharacter.stats.getStrength() + "', '"
                + newCharacter.stats.getDexterity() + "', '"
                + newCharacter.stats.getConstitution() +"', '"
                +newCharacter.stats.getIntelligence()+"', '"
                +newCharacter.stats.getWisdom()+"', '"
                +newCharacter.stats.getCharisma()+ "')";
        db.execSQL(sqlInsert);
        db.close();
    }

    public void updateStatsByCharacter(character updateCharacter){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlUpdate = "update "+TABLE_STATS
                +" set "+STRENGTH+" = "+updateCharacter.stats.getStrength()+", "
                +DEXTERITY+" = "+updateCharacter.stats.getDexterity()+", "
                +CONSTITUTION+" = "+updateCharacter.stats.getConstitution()+", "
                +INTELLIGENCE+" = "+updateCharacter.stats.getIntelligence()+", "
                +WISDOM+" = "+updateCharacter.stats.getWisdom()+", "
                +CHARISMA+" = "+updateCharacter.stats.getCharisma()
                +" where "+ID+" = 'C"+updateCharacter.getId()+"'";
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
        String sqlInsert = "insert into " + TABLE_CHARACTER
                + " values ('M"+newMonster.getId()+"', '"
                + newMonster.getName() + "', '"
                + newMonster.stats.getStrength() + "', '"
                + newMonster.stats.getDexterity() + "', '"
                + newMonster.stats.getConstitution() +"', '"
                +newMonster.stats.getIntelligence()+"', '"
                +newMonster.stats.getWisdom()+"', '"
                +newMonster.stats.getCharisma()+ "')";
        db.execSQL(sqlInsert);
        db.close();
    }

    public void updateStatsByMonster(monster updateMonster){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlUpdate = "update "+TABLE_STATS
                +" set "+STRENGTH+" = "+updateMonster.stats.getStrength()+", "
                +DEXTERITY+" = "+updateMonster.stats.getDexterity()+", "
                +CONSTITUTION+" = "+updateMonster.stats.getConstitution()+", "
                +INTELLIGENCE+" = "+updateMonster.stats.getIntelligence()+", "
                +WISDOM+" = "+updateMonster.stats.getWisdom()+", "
                +CHARISMA+" = "+updateMonster.stats.getCharisma()
                +" where "+ID+" = 'M"+updateMonster.getId()+"'";
        db.execSQL(sqlUpdate);
        db.close();
    }

    public void deleteStatsByMonster(monster deleteMonster){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from "+TABLE_STATS
                +" where "+ ID +" = 'M"+ deleteMonster.getId()+"'";
        db.execSQL(sqlDelete);
        db.close();
    }

    public void insertWeapon(weapon newWeapon){
        SQLiteDatabase db = this.getWritableDatabase();
        newWeapon.setId(Integer.parseInt(getNewWeaponID().substring(1)));
        Bitmap image = newWeapon.getImage();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        ContentValues cv = new ContentValues();
        cv.put("image",stream.toByteArray());
        String sqlInsert = "insert into " + TABLE_WEAPONS
                + " values ('"+getNewWeaponID()+"', '"
                + newWeapon.getWeaponType() + "', '"
                + newWeapon.getName() + "', '"
                + newWeapon.getDamage() + "', '"
                + newWeapon.getDamageType() +"', '"
                +newWeapon.getTraits()+"', '"
                +newWeapon.getProperty()+ "', '"
                +cv+"', '"
                +newWeapon.getImageName()+"')";
        db.execSQL(sqlInsert);
        db.close();
    }

    public void  updateWeapon(weapon weapon){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlupdate = "update "+TABLE_WEAPONS
                +" set "+WEAPON_TYPE+" = '"+ weapon.getWeaponType() +"', "
                +NAME+" = '"+weapon.getName()+"', "
                +DAMAGE+" = '"+weapon.getDamage()+"', "
                +DAMAGE_TYPE+" = '"+weapon.getDamageType()+"', "
                +TRAITS+" = '"+weapon.getTraits()+"', "
                +PROPERTY+" = '"+weapon.getProperty()
                +"' where "+ID+" = 'W"+weapon.getId()+"')";
        db.execSQL(sqlupdate);
        db.close();
    }

    public void deleteWeaponById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from "+TABLE_WEAPONS
                +" where "+ID+" = 'W"+id+"'" ;
        db.execSQL(sqlDelete);
        db.close();
    }

    public void insertArmor(armor newArmor){
        SQLiteDatabase db = this.getWritableDatabase();
        newArmor.setId(Integer.parseInt(getNewMonsterID().substring(1)));
        Bitmap image = newArmor.getImage();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        ContentValues cv = new ContentValues();
        cv.put("image",stream.toByteArray());
        String sqlInsert = "insert into "+TABLE_ARMOR+" values ('"+getNewArmorID()+"', '"
                +newArmor.getName()+"', '"
                +newArmor.getStrength()+"', '"
                +newArmor.getArmorClass()+"', '"
                +newArmor.getTraits()+"', '"
                +newArmor.getProperty()+"', '"
                +newArmor.getType()+"', '"
                +cv+"', '"
                +newArmor.getImageName()+"')";
        db.execSQL(sqlInsert);
        db.close();
    }

    private void updateArmor(armor armor){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlUpdate = "update "+TABLE_ARMOR
                +" set "+NAME+" = '"+ armor.getName()+"', "
                +ARMOR_CLASS+" = '"+armor.getArmorClass()+"', "
                +STRENGTH_REQUIREMENT+" = '"+armor.getStrength()+"', "
                +TRAITS+" = '"+armor.getTraits()+"', "
                +PROPERTY+" = '"+armor.getProperty()
                +"' where "+ID+" = 'A"+armor.getId()+"'";
        db.execSQL(sqlUpdate);
        db.close();
    }

    private void deleteArmorByID(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from "+TABLE_ARMOR+" where "+ID+" = 'A"+id+"'";
        db.execSQL(sqlDelete);
    }
}
