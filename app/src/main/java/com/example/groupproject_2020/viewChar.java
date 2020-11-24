package com.example.groupproject_2020;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class viewChar extends AppCompatActivity {
    private GridView gridView;
    public static ArrayList<character> charArray;
    DatabaseManager db;
    charAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewchararacter);

        gridView = (GridView) findViewById(R.id.gv_char);

        db = new DatabaseManager(this);
        charArray = new ArrayList<character>();

        charArray = db.selectAllCharacters();
        adapter = new charAdapter(viewChar.this, charArray);
        gridView.setAdapter(adapter);

    }
    public void previous(View v){
        this.finish();
    }
}
