package com.example.groupproject_2020;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class viewWeapon extends AppCompatActivity {

    private GridView gridView;
    public static ArrayList<weapon> weaponArray;
    DatabaseManager db;
    weaponAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewweapon);

        gridView = (GridView) findViewById(R.id.gv_weapon);

        db = new DatabaseManager(this);
        weaponArray = new ArrayList<weapon>();

        weaponArray = db.selectAllWeapons();
        adapter = new weaponAdapter(viewWeapon.this, weaponArray);
        gridView.setAdapter(adapter);

    }

    public void previous(View v) {
        this.finish();
    }
}
