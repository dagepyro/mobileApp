package com.example.groupproject_2020;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.fragment.NavHostFragment;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DatabaseManager db = new DatabaseManager(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_delete_char) {
            Intent deleteIntent = new Intent(this, DeleteCharActivity.class);
            this.startActivity(deleteIntent);
            return true;
        }
        if (id == R.id.action_delete_mon) {
            Intent deleteIntent = new Intent(this, DeleteMonActivity.class);
            this.startActivity(deleteIntent);
            return true;
        }

        if (id == R.id.action_view_char) {
            Intent viewIntent = new Intent(this, viewChar.class);
            this.startActivity(viewIntent);
            return true;
        }

        if (id == R.id.action_view_mon) {
            Intent viewIntent = new Intent(this, viewMon.class);
            this.startActivity(viewIntent);
            return true;
        }

        if (id == R.id.action_view_weapon) {
            Intent viewIntent = new Intent(this, viewWeapon.class);
            this.startActivity(viewIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}