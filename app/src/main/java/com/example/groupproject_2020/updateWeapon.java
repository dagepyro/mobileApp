package com.example.groupproject_2020;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class updateWeapon extends AppCompatActivity {

    DatabaseManager db;
    ArrayList<weapon> weapon;
    private ScrollView scroll;
    private int buttonWidth;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateweapon);
        db = new DatabaseManager(this);
        updateView();

        scroll = (ScrollView) findViewById(R.id.scrollView);
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        buttonWidth = size.x / 2;

    }

    public void updateView() {
        weapon = db.selectAllWeapons();

        if (weapon.size() > 0) {
            scroll = new ScrollView(this);
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(weapon.size());
            grid.setColumnCount(1);

            TextView[] ids = new TextView[weapon.size()];
            EditText[][] test = new EditText[weapon.size()][6];
            Button[] buttons = new Button[weapon.size()];
            ButtonHandler bh = new ButtonHandler();

            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            int i = 0;

            for (weapon w : weapon) {
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + (i + 1));

                test[i][0] = new EditText(this);
                test[i][1] = new EditText(this);
                test[i][2] = new EditText(this);
                test[i][3] = new EditText(this);
                test[i][4] = new EditText(this);
                test[i][5] = new EditText(this);
                test[i][0].setText("Name: " + w.getName());
                test[i][1].setText("" + w.getDamage());
                test[i][1].setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                test[i][2].setText("Dmg Type: " + w.getDamageType());
                test[i][3].setText("Weapon Type: " + w.getWeaponType());
                test[i][4].setText("Traits: " + w.getTraits());
                test[i][5].setText("Property: " + w.getProperty());
                test[i][0].setId(10 * w.getId());
                test[i][1].setId(10 * w.getId() +1);
                test[i][2].setId(10 * w.getId() +1);
                test[i][3].setId(10 * w.getId() +1);
                test[i][4].setId(10 * w.getId() +1);
                test[i][5].setId(10 * w.getId() +1);

                buttons[i] = new Button(this);
                buttons[i].setText("Update");
                buttons[i].setId(w.getId());
                buttons[i].setOnClickListener(bh);

                grid.addView(ids[i], width / 10, ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(test[i][0], (int) (width * .5), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(test[i][1], (int) (width * .5), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(test[i][2], (int) (width * .5), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(test[i][3], (int) (width * .5), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(test[i][4], (int) (width * .5), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(test[i][5], (int) (width * .5), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(buttons[i], (int) (width * .25), ViewGroup.LayoutParams.WRAP_CONTENT);

                i++;
            }

            Button backButton = new Button(this);
            backButton.setText(R.string.button_back);
            TextView emptyText = new TextView(this);
            grid.addView(emptyText, (int) (width / 10), ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(backButton, (int) (width * .25), ViewGroup.LayoutParams.WRAP_CONTENT);

            backButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    updateWeapon.this.finish();
                }
            });

            scroll.addView(grid);
            setContentView(scroll);
        }
    }

    private class ButtonHandler implements View.OnClickListener {

        public void onClick(View v) {
            int weaponId = v.getId();

            EditText nameET = (EditText) findViewById(weaponId);
            String name = nameET.getText().toString();

            EditText dmgET = (EditText)findViewById(weaponId);
            String damageString = dmgET.getText().toString();
            int damage = Integer.parseInt(damageString);

            EditText dmgTypeET = (EditText)findViewById(weaponId);
            String damageType = dmgTypeET.getText().toString();

            EditText weaponTypeET = (EditText)findViewById(weaponId);
            String weaponType = weaponTypeET.getText().toString();

            EditText traitsET = (EditText)findViewById(weaponId);
            String traits = traitsET.getText().toString();

            EditText propertyET = (EditText)findViewById(weaponId);
            String property = propertyET.getText().toString();


            weapon.get(weaponId).setName(name);
            weapon.get(weaponId).setDamage(damage);
            weapon.get(weaponId).setDamageType(damageType);
            weapon.get(weaponId).setWeaponType(weaponType);
            weapon.get(weaponId).setWeaponType(traits);
            weapon.get(weaponId).setWeaponType(property);

            db.updateWeapon(weapon.get(weaponId));

            try {
                db.updateWeapon(weapon.get(weaponId));
                Toast.makeText(updateWeapon.this, "Weapon has been updated", Toast.LENGTH_SHORT).show();

                updateView();
            } catch (NumberFormatException exc) {
                Toast.makeText(updateWeapon.this, "Error found with input", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
