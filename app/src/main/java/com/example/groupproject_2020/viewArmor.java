package com.example.groupproject_2020;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class viewArmor extends AppCompatActivity {
    private GridView gridView;
    public static ArrayList<armor> armorArray;
    DatabaseManager db;
    armorAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewarmor);

        gridView = (GridView) findViewById(R.id.gv_armor);

        db = new DatabaseManager(this);
        armorArray = new ArrayList<armor>();

        armorArray = db.selectAllArmor();
        adapter = new armorAdapter(viewArmor.this, armorArray);
        gridView.setAdapter(adapter);

    }

    public void previous(View v) {
        this.finish();
    }
}
