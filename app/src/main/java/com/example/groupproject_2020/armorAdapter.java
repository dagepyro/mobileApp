package com.example.groupproject_2020;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class armorAdapter extends BaseAdapter {
    Context context;
    ArrayList<armor> armorlist;
    private static LayoutInflater inflater = null;

    public armorAdapter(Context context, ArrayList<armor> empList) {
        this.context = context;
        this.armorlist = empList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return armorlist.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null)
            convertView = inflater.inflate(R.layout.armor_gridlayout, null);

        TextView nameTextView = (TextView) convertView.findViewById(R.id.armor_gridname);
        TextView strengthTextView = (TextView) convertView.findViewById(R.id.armor_gridstrength);
        TextView armorClassTextView = (TextView) convertView.findViewById(R.id.armor_gridarmorclass);
        TextView armorTraitTextView = (TextView) convertView.findViewById(R.id.armor_gridarmortrait);
        TextView armorPropertyTextView = (TextView) convertView.findViewById(R.id.armor_gridarmorproperty);
        TextView armorTypeTextView = (TextView) convertView.findViewById(R.id.armor_gridarmortype);

        armor e = new armor();
        e = armorlist.get(position);
        nameTextView.setText("Name: " + String.valueOf(e.getName()));
        strengthTextView.setText("Strength: " + String.valueOf(e.getStrength()));
        armorClassTextView.setText("Armor: " + String.valueOf(e.getArmorClass()));
        armorTraitTextView.setText("Traits: " + String.valueOf(e.getTraits()));
        armorPropertyTextView.setText("Property: " + String.valueOf(e.getProperty()));
        armorTypeTextView.setText("Armor Type: " + String.valueOf(e.getType()));

        return convertView;
    }
}
