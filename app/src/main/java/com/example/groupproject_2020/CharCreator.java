package com.example.groupproject_2020;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class CharCreator extends Fragment implements View.OnClickListener {

    private DatabaseManager dbManager;
    String  name,align,charclass,race;
    private Bitmap image;

    private static final int RESULT_LOAD_IMAGE = 1;

    ImageView uploadChar;
    Button uploadCharImage;
    EditText uploadCharImageName;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        dbManager = new DatabaseManager(getActivity());

        View view = inflater.inflate(R.layout.charcreator, container, false);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner alignspinner = getActivity().findViewById(R.id.align_spinner);
        ArrayAdapter<CharSequence> alignadapter = ArrayAdapter.createFromResource(getActivity(),R.array.alignment, android.R.layout.simple_spinner_item);
        alignadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        alignspinner.setAdapter(alignadapter);

        Spinner racespinner =  getActivity().findViewById(R.id.race_spinner);
        ArrayAdapter<CharSequence> raceadapter = ArrayAdapter.createFromResource(getActivity(),R.array.races, android.R.layout.simple_spinner_item);
        raceadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        racespinner.setAdapter(raceadapter);
        racespinner.setSelection(-1);

        Spinner classspinner =  getActivity().findViewById(R.id.class_spinner);
        ArrayAdapter<CharSequence> classadapter = ArrayAdapter.createFromResource(getActivity(),R.array.classes, android.R.layout.simple_spinner_item);
        classadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classspinner.setAdapter(classadapter);
        classspinner.setSelection(-1);

        alignspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    alignspinner.setOnItemSelectedListener(this);
                    align = adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        racespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                racespinner.setOnItemSelectedListener(this);
                race = (String) adapterView.getItemAtPosition(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        classspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                classspinner.setOnItemSelectedListener(this);
                charclass = (String) adapterView.getItemAtPosition(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        uploadChar = (ImageView) view.findViewById(R.id.char_image);
        uploadCharImageName = (EditText) view.findViewById(R.id.char_image_name);
        uploadChar.setOnClickListener(this);

        view.findViewById(R.id.BackButton).setOnClickListener(view12 ->
                NavHostFragment.findNavController(CharCreator.this)
                .navigate(R.id.action_SecondFragment_to_FirstFragment));

        view.findViewById(R.id.save_char).setOnClickListener(view1 -> {
            EditText nameET = view.findViewById(R.id.char_name);
            name = nameET.getText().toString();

            character newchar = new character(0,name,align,race,charclass,image,uploadCharImageName.getText().toString());
            dbManager.insertChar(newchar);
            Toast.makeText(getActivity(), name + " the " + align + " " + race + " " + charclass +  " was saved to the db", Toast.LENGTH_LONG).show();

        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.char_image:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null)  {
            Uri selectedImage = data.getData();
            try {
                 image = MediaStore.Images.Media.getBitmap(this.getContext().getContentResolver(), selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.w("data", "data: "+data);
            uploadChar.setImageBitmap(image);
        }
    }
}
