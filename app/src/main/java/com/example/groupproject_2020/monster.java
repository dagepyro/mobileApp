package com.example.groupproject_2020;

public class monster {
    private String name;
    int id, armorclass, hitpoints, exp;
    stats stats;

    public monster(int newId, String newName, int armorclass, int hitpoints, int exp) {
        setName(newName);
        setArmorclass(armorclass);
        setHitpoints(hitpoints);
        setExp(exp);
        setId(newId);
        stats = new stats(newId, 0, 0, 0, 0, 0, 0);
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
}
