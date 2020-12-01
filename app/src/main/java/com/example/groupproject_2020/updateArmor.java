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

public class updateArmor extends AppCompatActivity {
    DatabaseManager db;
    ArrayList<armor> armor;
    private ScrollView scroll;
    private int buttonWidth;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatearmor);
        db = new DatabaseManager(this);
        updateView();

        scroll = (ScrollView) findViewById(R.id.scrollView);
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        buttonWidth = size.x / 2;

    }

    public void updateView() {
        armor = db.selectAllArmor();

        if (armor.size() > 0) {
            scroll = new ScrollView(this);
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(armor.size());
            grid.setColumnCount(1);

            TextView[] ids = new TextView[armor.size()];
            EditText[][] test = new EditText[armor.size()][6];
            Button[] buttons = new Button[armor.size()];
            ButtonHandler bh = new ButtonHandler();

            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            int i = 0;

            for (armor w : armor) {
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + (i + 1));

                test[i][0] = new EditText(this);
                test[i][1] = new EditText(this);
                test[i][2] = new EditText(this);
                test[i][3] = new EditText(this);
                test[i][4] = new EditText(this);
                test[i][5] = new EditText(this);
                test[i][0].setText( w.getName());
                test[i][1].setText( "" + w.getStrength());
                test[i][1].setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                test[i][2].setText( w.getArmorClass());
                test[i][2].setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                test[i][3].setText( w.getTraits());
                test[i][4].setText( w.getProperty());
                test[i][5].setText( w.getType());
                test[i][0].setId(10 * w.getId());
                test[i][1].setId(10 * w.getId() + 1);
                test[i][2].setId(10 * w.getId() + 2);
                test[i][3].setId(10 * w.getId() + 3);
                test[i][4].setId(10 * w.getId() + 4);
                test[i][5].setId(10 * w.getId() + 5);

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
                    updateArmor.this.finish();
                }
            });

            scroll.addView(grid);
            setContentView(scroll);
        }
    }

    private class ButtonHandler implements View.OnClickListener {

        public void onClick(View v) {
            int armorId = v.getId();

            EditText nameET = (EditText) findViewById(10*armorId);
            String name = nameET.getText().toString();

            EditText strET = (EditText)findViewById(10*armorId+1);
            String strString = strET.getText().toString();
            int strength = Integer.parseInt(strString);

            EditText armorClassET = (EditText)findViewById(10*armorId+2);
            String armorClass = armorClassET.getText().toString();

            EditText traitsET = (EditText)findViewById(10*armorId+4);
            String traits = traitsET.getText().toString();

            EditText propertyET = (EditText)findViewById(10*armorId+5);
            String property = propertyET.getText().toString();

            EditText armorTypeET = (EditText)findViewById(10*armorId+3);
            String armorType = armorTypeET.getText().toString();

            armor.get(armorId).setName(name);
            armor.get(armorId).setStrength(strength);
            armor.get(armorId).setArmorClass(armorClass);
            armor.get(armorId).setTraits(traits);
            armor.get(armorId).setProperty(property);
            armor.get(armorId).setType(armorType);

            db.updateArmor(armor.get(armorId));

            try {
                db.updateArmor(armor.get(armorId));
                Toast.makeText(updateArmor.this, "Armor has been updated", Toast.LENGTH_SHORT).show();

                updateView();
            } catch (NumberFormatException exc) {
                Toast.makeText(updateArmor.this, "Error found with input", Toast.LENGTH_SHORT).show();
            }
        }
    }
}