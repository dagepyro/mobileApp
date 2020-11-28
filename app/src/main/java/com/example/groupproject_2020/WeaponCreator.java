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

public class WeaponCreator extends Fragment {
    private DatabaseManager dbManager;
    private Bitmap image;

    private static final int RESULT_LOAD_IMAGE = 1;

    ImageView uploadWeapon;
    Button uploadWeaponImage;
    EditText uploadWeaponImageName;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        dbManager = new DatabaseManager(getActivity());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.weapon, container, false);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        uploadWeapon = (ImageView) view.findViewById(R.id.weapon_image);
        uploadWeaponImage = (Button) view.findViewById(R.id.upload_weapon_image);
        uploadWeaponImageName = (EditText) view.findViewById(R.id.weapon_image_name);
        uploadWeapon.setOnClickListener(this::onClick);
        uploadWeaponImage.setOnClickListener(this::onClick);

        view.findViewById(R.id.BackButton).setOnClickListener(view12 ->
                NavHostFragment.findNavController(WeaponCreator.this)
                        .navigate(R.id.action_Weapon_Creator_to_Smithy_Nav));

        view.findViewById(R.id.save_weapon).setOnClickListener(view1 -> {
            EditText weaponnameET = view.findViewById(R.id.weap_name);
            String name = weaponnameET.getText().toString();

            EditText dmgET = view.findViewById(R.id.dmg);
            String damageString = dmgET.getText().toString();
            int damage = Integer.parseInt(damageString);

            EditText dmgTypeET = view.findViewById(R.id.dmg_type);
            String damageType = dmgTypeET.getText().toString();

            EditText weaponTypeET = view.findViewById(R.id.weap_type);
            String weaponType = weaponTypeET.getText().toString();

            EditText traitsET = view.findViewById(R.id.weap_traits);
            String traits = traitsET.getText().toString();

            EditText propertyET = view.findViewById(R.id.weap_property);
            String property = propertyET.getText().toString();

            weapon newweapon = new weapon(0,name,damage,damageType, weaponType, traits, property,image, uploadWeaponImageName.getText().toString());

            dbManager.insertWeapon(newweapon);

            Toast.makeText(getActivity(), "the " + name + " with " + damage + damageType + weaponType
                    + traits + property +" was saved to the db", Toast.LENGTH_SHORT).show();

            weaponnameET.setText("");
            dmgET.setText("");
            dmgTypeET.setText("");
            weaponTypeET.setText("");
            traitsET.setText("");
            propertyET.setText("");
        });
    }

    public void onClick(View v) {
        switch(v.getId()){
            case R.id.weapon_image:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
            case R.id.upload_weapon_image:

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
            uploadWeapon.setImageBitmap(image);
        }
    }
}
