package com.example.groupproject_2020;

public class character {
    private String name, alignment, charclass, race;

    public character(String newName, String newAlignment, String newCharclass, String newRace){
        setName(newName);
        setAlignment(newAlignment);
        setCharclass(newCharclass);
        setRace(newRace);
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
}
