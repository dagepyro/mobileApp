package com.example.groupproject_2020;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class Smithy extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.smithy, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Smithy.this)
                        .navigate(R.id.action_Smithy_Nav_to_Main_Menu_Nav);
            }
        });

        view.findViewById(R.id.Weapon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Smithy.this)
                        .navigate(R.id.action_Smithy_Nav_to_Weapon_Creator);
            }
        });

        view.findViewById(R.id.Armor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Smithy.this)
                        .navigate(R.id.action_Smithy_Nav_to_Armor_Creator);
            }
        });
    }
}