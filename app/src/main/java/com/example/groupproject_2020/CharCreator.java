package com.example.groupproject_2020;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class CharCreator extends Fragment {
    private DatabaseManager dbManager;
    String align,charclass,race;

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

        view.findViewById(R.id.BackButton).setOnClickListener(view12 ->
                NavHostFragment.findNavController(CharCreator.this)
                .navigate(R.id.action_SecondFragment_to_FirstFragment));

        view.findViewById(R.id.save_char).setOnClickListener(view1 -> {
            EditText nameET = view.findViewById(R.id.char_name);
            String name = nameET.getText().toString();



//            EditText alignET = view.findViewById(R.id.align_spinner);
//            String align = alignET.getText().toString();

//            EditText raceET = view.findViewById(R.id.char_race);
//            String race = raceET.getText().toString();
//
//            EditText classET = view.findViewById(R.id.char_class);
//            String charclass = classET.getText().toString();

            character newchar = new character(name,align,charclass,race);
            dbManager.insertChar(newchar);
            Toast.makeText(getActivity(), name + " the  " + align + " " + race + " " + charclass +  " was saved to the db", Toast.LENGTH_LONG).show();

        });
    }
}
