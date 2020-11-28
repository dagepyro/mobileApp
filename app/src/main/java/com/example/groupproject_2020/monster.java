package com.example.groupproject_2020;

import android.graphics.Bitmap;

public class monster {
    private String name, imageName;
    private int id, armorclass, hitpoints, exp;
    private Bitmap image;
    protected stats stats;

    public monster(int newId, String newName, int armorclass, int hitpoints, int exp, Bitmap image, String imageName) {
        setName(newName);
        setArmorclass(armorclass);
        setHitpoints(hitpoints);
        setExp(exp);
        setId(newId);
        setImage(image);
        setImageName(imageName);
        stats = new stats(newId,0,0,0,0,0,0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArmorclass() {
        return armorclass;
    }

    public void setArmorclass(int armorclass) {
        this.armorclass = armorclass;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public stats getStats() {
        return stats;
    }

    public void setStats(stats stats) {
        this.stats = stats;
    }
}
