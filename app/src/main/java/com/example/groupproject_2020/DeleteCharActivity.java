package com.example.groupproject_2020;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DeleteCharActivity extends AppCompatActivity {
    private DatabaseManager dbManager;
    private ArrayList<character> characterArrayList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    public void updateView() {

        characterArrayList = dbManager.selectAllCharacters();

        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);

        RadioGroup group = new RadioGroup(this);
        for (character character : characterArrayList) {
            RadioButton rb = new RadioButton(this);
            rb.setId(characterArrayList.indexOf(character));
            rb.setText(character.getName());
            rb.setTag(character);
            group.addView(rb);
        }

        RadioButtonHandler rbh = new RadioButtonHandler();
        group.setOnCheckedChangeListener(rbh);

        Button backButton = new Button(this);
        backButton.setBackgroundResource(R.drawable.button);
        backButton.setText(R.string.button_back);

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DeleteCharActivity.this.finish();
            }
        });

        scrollView.addView(group);
        layout.addView(scrollView);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, 0, 0, 50);
        layout.addView(backButton, params);

        setContentView(layout);
    }

    private class RadioButtonHandler implements RadioGroup.OnCheckedChangeListener {
        public void onCheckedChanged(RadioGroup group, int charId) {

            dbManager.deleteCharacter(characterArrayList.get(charId));

            Toast.makeText(DeleteCharActivity.this, "Character is toast!", Toast.LENGTH_SHORT).show();

            updateView();
        }
    }
}
