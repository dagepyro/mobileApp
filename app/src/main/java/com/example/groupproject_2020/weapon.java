package com.example.groupproject_2020;

import android.graphics.Bitmap;

public class weapon {
    private int id, damage;
    private String name, damageType, weaponType, traits, property, imageName;
    private Bitmap image;

    public weapon(int id, String name, int damage, String damageType, String weaponType, String traits, String property, Bitmap image, String imageName) {
        this.id = id;
        this.damage = damage;
        this.name = name;
        this.damageType = damageType;
        this.weaponType = weaponType;
        this.traits = traits;
        this.property = property;
        this.image = image;
        this.imageName = imageName;
    }

    public weapon(int id, String name, int damage, String damageType, String weaponType, String traits, String property) {
        this.id = id;
        this.damage = damage;
        this.name = name;
        this.damageType = damageType;
        this.weaponType = weaponType;
        this.traits = traits;
        this.property = property;
    }

    public weapon() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public String getTraits() {
        return traits;
    }

    public void setTraits(String traits) {
        this.traits = traits;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
