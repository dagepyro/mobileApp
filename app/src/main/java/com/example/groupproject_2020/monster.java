package com.example.groupproject_2020;

public class monster {
    private String name, armorclass, hitpoints, exp;

    public monster(String newName, String armorclass, String hitpoints, String exp){
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

    public String getArmorclass() {
        return armorclass;
    }

    public void setArmorclass(String armorclass) {
        this.armorclass = armorclass;
    }

    public String getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(String hitpoints) {
        this.hitpoints = hitpoints;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String race) { this.exp= exp;
    }
}
