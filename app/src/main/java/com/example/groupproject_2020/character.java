package com.example.groupproject_2020;

import android.graphics.Bitmap;
import android.net.Uri;

public class character {
    private String name, alignment, charclass, race;
    private int id;
    private Bitmap image;
    stats stats;

    public character(int newId, String newName, String newAlignment, String newCharclass, String newRace, Bitmap image){
        setName(newName);
        setAlignment(newAlignment);
        setCharclass(newCharclass);
        setRace(newRace);
        setId(newId);
        setImage(image);
        stats = new stats(0,0,0,0,0,0,0);
    }

    public character(int newId, String newName, String newAlignment, String newCharclass, String newRace){
        setName(newName);
        setAlignment(newAlignment);
        setCharclass(newCharclass);
        setRace(newRace);
        setId(newId);
        stats = new stats(0,0,0,0,0,0,0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getCharclass() {
        return charclass;
    }

    public void setCharclass(String charclass) {
        this.charclass = charclass;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
