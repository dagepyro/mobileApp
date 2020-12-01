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

public class updatechar_stats extends AppCompatActivity {

    DatabaseManager db;
    ArrayList<character> pc;
    private ScrollView scroll;
    private int buttonWidth;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatechar);
        db = new DatabaseManager(this);
        updateView();

        scroll = (ScrollView) findViewById(R.id.scrollView);
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        buttonWidth = size.x / 2;

    }

    public void updateView() {
        pc = db.selectAllCharacters();

        if (pc.size() > 0) {
            scroll = new ScrollView(this);
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(pc.size());
            grid.setColumnCount(1);

            TextView[] ids = new TextView[pc.size()];
            EditText[][] test = new EditText[pc.size()][7];
            Button[] buttons = new Button[pc.size()];
            ButtonHandler bh = new ButtonHandler();

            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            int i = 0;

            for (character ch : pc) {
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + (i + 1));

                test[i][0] = new EditText(this);
                test[i][0].setText(ch.getName());
                test[i][0].setId(10 * ch.getId());

                test[i][1] = new EditText(this);
                test[i][1].setText(""+pc.get(i).getStats().getStrength());
                test[i][1].setId(10 * ch.getId()+1);
                test[i][1].setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

                test[i][2] = new EditText(this);
                test[i][2].setText(""+pc.get(i).getStats().dexterity);
                test[i][2].setId(10 * ch.getId()+2);
                test[i][2].setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

                test[i][3] = new EditText(this);
                test[i][3].setText(""+pc.get(i).getStats().constitution);
                test[i][3].setId(10 * ch.getId()+3);
                test[i][3].setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

                test[i][4] = new EditText(this);
                test[i][4].setText(""+pc.get(i).getStats().intelligence);
                test[i][4].setId(10 * ch.getId()+4);
                test[i][4].setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

                test[i][5] = new EditText(this);
                test[i][5].setText(""+pc.get(i).getStats().wisdom);
                test[i][5].setId(10 * ch.getId()+5);
                test[i][5].setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

                test[i][6] = new EditText(this);
                test[i][6].setText(""+pc.get(i).getStats().charisma);
                test[i][6].setId(10 * ch.getId()+6);
                test[i][6].setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);



                buttons[i] = new Button(this);
                buttons[i].setText("Update");
                buttons[i].setId(ch.getId());
                buttons[i].setOnClickListener(bh);

                grid.addView(ids[i], width / 10, ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(test[i][0], (int) (width * .5), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(test[i][1], (int) (width * .5), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(test[i][2], (int) (width * .5), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(test[i][3], (int) (width * .5), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(test[i][4], (int) (width * .5), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(test[i][5], (int) (width * .5), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(test[i][6], (int) (width * .5), ViewGroup.LayoutParams.WRAP_CONTENT);
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
                    updatechar_stats.this.finish();
                }
            });

            scroll.addView(grid);
            setContentView(scroll);
        }
    }

    private class ButtonHandler implements View.OnClickListener {

        public void onClick(View v) {
            int charId = v.getId();
            EditText nameET = (EditText) findViewById(10*charId);
            String name = nameET.getText().toString();

            EditText strET = (EditText) findViewById(10*charId+1);
            String str = strET.getText().toString();
            int strength = Integer.parseInt(str);

            EditText dexET = (EditText) findViewById(10*charId+2);
            String dex = dexET.getText().toString();
            int dexterity = Integer.parseInt(dex);

            EditText conET = (EditText) findViewById(10*charId+3);
            String con = conET.getText().toString();
            int constitution = Integer.parseInt(con);

            EditText intEt = (EditText) findViewById(10*charId+4);
            String intel = intEt.getText().toString();
            int intelligence = Integer.parseInt(intel);

            EditText wisET = (EditText) findViewById(10*charId+5);
            String wis = wisET.getText().toString();
            int wisdom = Integer.parseInt(wis);

            EditText chaET = (EditText) findViewById(10*charId+6);
            String cha = chaET.getText().toString();
            int charisma = Integer.parseInt(cha);


            pc.get(charId).setName(name);
            pc.get(charId).setStats(new stats(charId,strength,dexterity,constitution,intelligence,wisdom,charisma));
//            pc.get(charId).setStats(pc.get(charId).stats);
//            db.updateCharacter(pc.get(charId));

            try {
                db.updateCharacter(pc.get(charId));
                db.updateStatsByCharacter(pc.get(charId));
                Toast.makeText(updatechar_stats.this, "Character has been updated", Toast.LENGTH_SHORT).show();

                updateView();
            } catch (NumberFormatException exc) {
                Toast.makeText(updatechar_stats.this, "Error found with input", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
