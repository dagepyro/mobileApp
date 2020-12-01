package com.example.groupproject_2020;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class character implements Parcelable {
    DatabaseManager db;
    private String name;
    private String alignment;
    private String charclass;
    private String race;
    private  String imageName;
    private int id;
    private Bitmap image;
    public stats stats;

    public character(int newId, String newName, String newAlignment, String newRace, String newCharclass, Bitmap image, String imageName) {
        setName(newName);
        setAlignment(newAlignment);
        setCharclass(newCharclass);
        setRace(newRace);
        setId(newId);
        setImage(image);
        setImageName(imageName);
        stats = new stats(0, 0, 0, 0, 0, 0, 0);
    }

    public character(int newId, String newName, String newAlignment, String newRace, String newCharclass) {
        setName(newName);
        setAlignment(newAlignment);
        setCharclass(newCharclass);
        setRace(newRace);
        setId(newId);
        stats = new stats(0, 0, 0, 0, 0, 0, 0);
    }

    public character() {

    }
    public character(int newId, String newName) {
        setId(newId);
        setName(newName);

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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }




    protected character(Parcel in) {
        db = (DatabaseManager) in.readValue(DatabaseManager.class.getClassLoader());
        name = in.readString();
        alignment = in.readString();
        charclass = in.readString();
        race = in.readString();
        imageName = in.readString();
        id = in.readInt();
        image = (Bitmap) in.readValue(Bitmap.class.getClassLoader());
        stats = (stats) in.readValue(stats.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(db);
        dest.writeString(name);
        dest.writeString(alignment);
        dest.writeString(charclass);
        dest.writeString(race);
        dest.writeString(imageName);
        dest.writeInt(id);
        dest.writeValue(image);
        dest.writeValue(stats);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<character> CREATOR = new Parcelable.Creator<character>() {
        @Override
        public character createFromParcel(Parcel in) {
            return new character(in);
        }

        @Override
        public character[] newArray(int size) {
            return new character[size];
        }
    };
}

//package com.example.groupproject_2020;
//
//import android.graphics.Bitmap;
//
//public class character {
//    DatabaseManager db;
//    private String name;
//    private String alignment;
//    private String charclass;
//    private String race;
//    private  String imageName;
//    private int id;
//    private Bitmap image;
//    protected stats stats;
//
//    public character(int newId, String newName, String newAlignment, String newRace, String newCharclass, Bitmap image, String imageName) {
//        setName(newName);
//        setAlignment(newAlignment);
//        setCharclass(newCharclass);
//        setRace(newRace);
//        setId(newId);
//        setImage(image);
//        setImageName(imageName);
//        stats = new stats(0, 0, 0, 0, 0, 0, 0);
//    }
//
//    public character(int newId, String newName, String newAlignment, String newRace, String newCharclass) {
//        setName(newName);
//        setAlignment(newAlignment);
//        setCharclass(newCharclass);
//        setRace(newRace);
//        setId(newId);
//        stats = new stats(0, 0, 0, 0, 0, 0, 0);
//    }
//
//    public character() {
//
//    }
//    public character(int newId, String newName) {
//        setId(newId);
//        setName(newName);
//
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getAlignment() {
//        return alignment;
//    }
//
//    public void setAlignment(String alignment) {
//        this.alignment = alignment;
//    }
//
//    public String getCharclass() {
//        return charclass;
//    }
//
//    public void setCharclass(String charclass) {
//        this.charclass = charclass;
//    }
//
//    public String getRace() {
//        return race;
//    }
//
//    public void setRace(String race) {
//        this.race = race;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public Bitmap getImage() {
//        return image;
//    }
//
//    public void setImage(Bitmap image) {
//        this.image = image;
//    }
//
//    public stats getStats() {
//        return stats;
//    }
//
//    public void setStats(stats stats) {
//        this.stats = stats;
//    }
//
//    public String getImageName() {
//        return imageName;
//    }
//
//    public void setImageName(String imageName) {
//        this.imageName = imageName;
//    }
//
//
//
//}
