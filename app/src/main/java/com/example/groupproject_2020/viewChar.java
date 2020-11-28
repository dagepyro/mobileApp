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
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class viewChar extends AppCompatActivity {
    private GridView gridView;
    public static ArrayList<String> charArray = new ArrayList<String>();
    DatabaseManager db;

    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        db = new DatabaseManager(this);
        setContentView(R.layout.viewchararacter );

//
//        List<character> characters = db.selectAllCharacters();
//
//        for (character ch : characters){
//            charArray = "Name: " + ch.getName() + ch.getAlignment() + ch.getRace() + ch.getCharclass();
//        }
//
//        gridView = (GridView) findViewById(R.id.gridView1);
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, charArray);
//
//        gridView.setAdapter(adapter);






        viewCharacter();
    }

   public void viewCharacter( ) {

       //ArrayList for candies
        ArrayList<character> chara = db.selectAllCharacters();

        if(chara.size() > 0) {
            // create ScrollView and GridLayout
            ScrollView scrollView = new ScrollView( this );
            GridLayout grid = new GridLayout( this );
            grid.setRowCount( chara.size( ) );
            grid.setColumnCount( 4 );

            // create arrays of components
            TextView[] ids = new TextView[chara.size( )];
            TextView[][] charviews = new TextView[chara.size( )][4];


            // retrieve width of screen
            Point size = new Point( );
            getWindowManager( ).getDefaultDisplay( ).getSize( size );
            int width = size.x;

            int i = 0;

            for (character character : chara ) {
                // create the TextView for the candy's id
                ids[i] = new TextView( this );
                ids[i].setGravity( Gravity.CENTER );
                ids[i].setText( "" + (i+1) );

              // create the two EditTexts for the candy's name and price
                charviews[i][0] = new TextView( this );
                charviews[i][1] = new TextView( this );
               charviews[i][2] = new TextView( this );
               charviews[i][3] = new TextView( this );
                charviews[i][0].setText( "Name " + character.getName( ) );
                charviews[i][1].setText( "Alignment " + character.getAlignment( ));
                charviews[i][2].setText("Class " + character.getCharclass( ));
                charviews[i][3].setText("Race " + character.getRace( ));


                // add the elements to grid

                grid.addView( ids[i], width/10, ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( charviews[i][0], ( int ) ( width * .4 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( charviews[i][1], ( int ) ( width * .25 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( charviews[i][2], ( int ) ( width * .4 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( charviews[i][3], ( int ) ( width * .25 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );

                i++;
            }

            // create a back button
            Button backButton = new Button(this);
            backButton.setText(R.string.previous);
            TextView emptyText = new TextView(this);
            grid.addView(emptyText,( int ) ( width / 10 ), ViewGroup.LayoutParams.WRAP_CONTENT );
            grid.addView( backButton, ( int ) ( width * .15 ),ViewGroup.LayoutParams.WRAP_CONTENT );

            backButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    viewChar.this.finish();
                }
            });
            scrollView.addView( grid );
            setContentView( scrollView );
        }
    }
}
