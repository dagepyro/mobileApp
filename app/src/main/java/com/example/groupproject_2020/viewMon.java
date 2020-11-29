package com.example.groupproject_2020;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class viewMon extends AppCompatActivity {
    private GridView gridView;
    public static ArrayList<monster> monArray;
    DatabaseManager db;
    monAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewmonster);

        gridView = (GridView) findViewById(R.id.gv_mon);

        db = new DatabaseManager(this);
        monArray = new ArrayList<monster>();

        monArray = db.selectAllMonsters();
        adapter = new monAdapter(viewMon.this, monArray);
        gridView.setAdapter(adapter);

    }

    public void previous(View v) {
        this.finish();
    }
}
