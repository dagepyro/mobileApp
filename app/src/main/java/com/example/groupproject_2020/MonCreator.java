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

public class MonCreator extends Fragment {
    private DatabaseManager dbManager;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        dbManager = new DatabaseManager(getActivity());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.moncreator, container, false);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.BackButton).setOnClickListener(view12 ->
                NavHostFragment.findNavController(MonCreator.this)
                        .navigate(R.id.action_Mon_Creator_Nav_to_Main_Menu_Nav));

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

            monster newmon = new monster(name,armorclass,hp,exp);
            dbManager.insertMonster(newmon);
            Toast.makeText(getActivity(), name + " was saved to the db", Toast.LENGTH_SHORT).show();

            monnameET.setText("");
            armorclassET.setText("");
            hpET.setText("");
            expET.setText("");
        });
    }

}