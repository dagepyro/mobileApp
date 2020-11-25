package com.example.groupproject_2020;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import static android.app.Activity.RESULT_OK;

public class MonCreator extends Fragment {
    private DatabaseManager dbManager;

    private static final int RESULT_LOAD_IMAGE = 1;

    ImageView uploadMon;
    EditText uploadMonImageName;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        dbManager = new DatabaseManager(getActivity());

        View view = inflater.inflate(R.layout.moncreator, container, false);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        uploadMon = (ImageView) view.findViewById(R.id.mon_image);
        uploadMonImageName = (EditText) view.findViewById(R.id.mon_image_name);
        uploadMon.setOnClickListener(this::onClick);

        view.findViewById(R.id.BackButton).setOnClickListener(view12 ->
                NavHostFragment.findNavController(MonCreator.this)
                        .navigate(R.id.action_Mon_Creator_Nav_to_Main_Nav));

        view.findViewById(R.id.save_mon).setOnClickListener(view1 -> {
            EditText monnameET = view.findViewById(R.id.mon_name);
            String name = monnameET.getText().toString();

            EditText armorclassET = view.findViewById(R.id.armor_class);
            String armorclassString = armorclassET.getText().toString();
            int armorclass = Integer.parseInt(armorclassString);

            EditText hpET = view.findViewById(R.id.hp);
            String hpString = hpET.getText().toString();
            int hp = Integer.parseInt(hpString);

            EditText expET = view.findViewById(R.id.exp);
            String expString = expET.getText().toString();
            int exp = Integer.parseInt(expString);

            monster newmon = new monster(0, name, armorclass, hp, exp);

            dbManager.insertMonster(newmon);

            Toast.makeText(getActivity(), "the " + name + " with " + armorclass + " armor class " + hp
                    + " hp and " + exp + " exp was saved to the db", Toast.LENGTH_SHORT).show();

            monnameET.setText("");
            armorclassET.setText("");
            hpET.setText("");
            expET.setText("");
        });
    }

    public void onClick(View v) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            uploadMon.setImageURI(selectedImage);
        }
    }
}