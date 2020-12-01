package com.example.groupproject_2020;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class updateChar extends AppCompatActivity {

    DatabaseManager db;
    ArrayList<character> pc;
    private ScrollView scroll;
    private int buttonWidth;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatechar);
        db = new DatabaseManager(this);
        scroll = ( ScrollView ) findViewById( R.id.scrollView );
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        buttonWidth = size.x/2;
        updateView();
    }

    public void updateView(){

        pc = db.selectAllCharacters();
        if(pc.size() > 0){
         //   scroll.removeAllViewsInLayout();
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(pc.size());
            grid.setColumnCount(2);

            pcButton[] buttons = new pcButton[pc.size()];
            ButtonHandler bh = new ButtonHandler();

            int i = 0; String pcInfo;
            for(character chars : pc){
                buttons[i] = new pcButton(this, chars);
                pcInfo = chars.getName() + " ";
                buttons[i].setText(pcInfo);

                buttons[i].setOnClickListener(bh);

                grid.addView(buttons[i],buttonWidth,GridLayout.LayoutParams.WRAP_CONTENT);
                i++;
            }
            scroll.addView(grid);
        }
    }

    private class ButtonHandler implements View.OnClickListener{

        @Override
        public void onClick(View view) {

        }
    }
//        pc = db.selectAllCharacters();
//
//        if(pc.size() > 0){
//            ScrollView scroll = new ScrollView(this);
//            GridLayout grid = new GridLayout(this);
//            grid.setRowCount(pc.size());
//            grid.setColumnCount(4);
//
//            TextView[] ids = new TextView[pc.size()];
//            EditText[] test = new EditText[pc.size()];
//            Button[] buttons = new Button[pc.size()];
//            ButtonHandler bh = new ButtonHandler();
//
//            Point size = new Point();
//            getWindowManager().getDefaultDisplay().getSize(size);
//            int width = size.x;
//
//            int i = 0;
//
//            for (character ch : pc){
//                ids[i] = new TextView(this);
//                ids[i].setGravity(Gravity.CENTER);
//                ids[i].setText("" + (i+1));
//
//                test[i] = new EditText(this);
//               // test[i][1] = new EditText(this);
//                test[i].setText(ch.getName());
//                test[i].setId(pc.indexOf(ch));
//
//                buttons[i] = new Button(this);
//                buttons[i].setText("Update");
//                buttons[i].setId(ch.getId());
//
//                buttons[i].setOnClickListener(bh);
//
//               // grid.addView(ids[i], width/10, ViewGroup.LayoutParams.WRAP_CONTENT);
//                grid.addView(test[i], (int) (width * .20), ViewGroup.LayoutParams.WRAP_CONTENT);
//                //grid.addView(test[i], (int) (width * .25), ViewGroup.LayoutParams.WRAP_CONTENT);
//                grid.addView(buttons[i], (int) (width * .40), ViewGroup.LayoutParams.WRAP_CONTENT);
//
//                i++;
//            }
//            Button backButton = new Button(this);
//            backButton.setText(R.string.button_back);
//            TextView emptyText = new TextView(this);
//            grid.addView(emptyText,( int ) ( width / 10 ), ViewGroup.LayoutParams.WRAP_CONTENT );
//            grid.addView( backButton, ( int ) ( width * .15 ),ViewGroup.LayoutParams.WRAP_CONTENT );
//
//            backButton.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    updateChar.this.finish();
//                }
//            });
//            scroll.addView( grid );
//            setContentView( scroll );
//
//        }
//        }
//
//    private class ButtonHandler implements View.OnClickListener{
//
//        public void onClick(View v){
//            int charId = v.getId();
//            EditText nameET = (EditText) findViewById(charId);
//            String name = nameET.getText().toString();
//
//
//                pc.get(charId).setName(name);
//                pc.get(charId).setStats(pc.get(charId).stats);
//                db.updateCharacter(pc.get(charId));
//
//        }
//
//    }
    }


