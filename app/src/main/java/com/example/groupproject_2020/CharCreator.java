package com.example.groupproject_2020;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class CharCreator extends Fragment {
    private DatabaseManager dbManager;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        dbManager = new DatabaseManager(getActivity());


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.charcreator, container, false);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.BackButton).setOnClickListener(view12 ->
                NavHostFragment.findNavController(CharCreator.this)
                .navigate(R.id.action_SecondFragment_to_FirstFragment));

        view.findViewById(R.id.save_char).setOnClickListener(view1 -> {
            EditText nameET = view.findViewById(R.id.char_name);
            String name = nameET.getText().toString();

            EditText alignET = view.findViewById(R.id.char_align);
            String align = alignET.getText().toString();

            EditText raceET = view.findViewById(R.id.char_race);
            String race = raceET.getText().toString();

            EditText classET = view.findViewById(R.id.char_class);
            String charclass = classET.getText().toString();

            double id = (Math.random()*10);

            character newchar = new character(name,align,charclass,race,id);
            dbManager.insertChar(newchar);
            Toast.makeText(getActivity(), name + " was saved to the db", Toast.LENGTH_SHORT).show();

            nameET.setText("");
            alignET.setText("");
            raceET.setText("");
            classET.setText("");
        });
    }

}