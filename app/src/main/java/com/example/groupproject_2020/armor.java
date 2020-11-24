package com.example.groupproject_2020;

public class armor {
    int id, strength;
    String name, armorClass, traits, property, type;

    public armor(int id, String name, int strength, String armorClass, String traits, String property, String type) {
        this.id = id;
        this.strength = strength;
        this.name = name;
        this.armorClass = armorClass;
        this.traits = traits;
        this.property = property;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(String armorClass) {
        this.armorClass = armorClass;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
