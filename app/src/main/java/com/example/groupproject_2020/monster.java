package com.example.groupproject_2020;

public class monster {
    private String name;
    int armorclass, hitpoints, exp;

    public monster(String newName, int armorclass, int hitpoints, int exp) {
        setName(newName);
        setArmorclass(armorclass);
        setHitpoints(hitpoints);
        setExp(exp);
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
}
