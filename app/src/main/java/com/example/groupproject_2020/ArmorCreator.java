package com.example.groupproject_2020;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class ArmorCreator extends Fragment {

    private DatabaseManager dbManager;
    private Bitmap image;

    private static final int RESULT_LOAD_IMAGE = 1;

    ImageView uploadArmor;
    Button uploadArmorImage;
    EditText uploadArmorImageName;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        dbManager = new DatabaseManager(getActivity());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.armor, container, false);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        uploadArmor = (ImageView) view.findViewById(R.id.armor_image);
        uploadArmorImage = (Button) view.findViewById(R.id.upload_armor_image);
        uploadArmorImageName = (EditText) view.findViewById(R.id.armor_image_name);
        uploadArmor.setOnClickListener(this::onClick);
        uploadArmorImage.setOnClickListener(this::onClick);

        view.findViewById(R.id.BackButton).setOnClickListener(view12 ->
                NavHostFragment.findNavController(ArmorCreator.this)
                        .navigate(R.id.action_Armor_Creator_to_Smithy_Nav));

        view.findViewById(R.id.save_armor).setOnClickListener(view1 -> {
            EditText armornameET = view.findViewById(R.id.arm_name);
            String name = armornameET.getText().toString();

            EditText armStrET = view.findViewById(R.id.arm_str);
            String armStrString = armStrET.getText().toString();
            int strength = Integer.parseInt(armStrString);

            EditText armClassET = view.findViewById(R.id.arm_class);
            String armorClass = armClassET.getText().toString();

            EditText armTraitsET = view.findViewById(R.id.arm_traits);
            String traits = armTraitsET.getText().toString();

            EditText armPropET = view.findViewById(R.id.arm_property);
            String property = armPropET.getText().toString();

            EditText armTypeET = view.findViewById(R.id.arm_type);
            String type = armTypeET.getText().toString();

            armor newarmor = new armor(0, name, strength, armorClass, traits, property, type, image, uploadArmorImageName.getText().toString());

            dbManager.insertArmor(newarmor);

            Toast.makeText(getActivity(), "the " + name + " with " + strength + armorClass + traits
                    + property + type +" was saved to the db", Toast.LENGTH_SHORT).show();

            armornameET.setText("");
            armStrET.setText("");
            armClassET.setText("");
            armTraitsET.setText("");
            armPropET.setText("");
            armTypeET.setText("");
        });
    }

    public void onClick(View v) {
        switch(v.getId()){
            case R.id.armor_image:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
            case R.id.upload_armor_image:

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
            uploadArmor.setImageBitmap(image);
        }
    }
}
