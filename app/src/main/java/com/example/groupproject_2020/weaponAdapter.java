package com.example.groupproject_2020;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class weaponAdapter extends BaseAdapter {

    Context context;
    ArrayList<weapon> weaponlist;
    private static LayoutInflater inflater = null;

    public weaponAdapter(Context context, ArrayList<weapon> empList) {
        this.context = context;
        this.weaponlist = empList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return weaponlist.size();
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
            convertView = inflater.inflate(R.layout.weapon_gridlayout, null);

        TextView nameTextView = (TextView) convertView.findViewById(R.id.weapon_gridname);
        TextView damageTextView = (TextView) convertView.findViewById(R.id.weapon_griddamage);
        TextView dmgTypeTextView = (TextView) convertView.findViewById(R.id.weapon_griddmgtype);
        TextView weapTypeTextView = (TextView) convertView.findViewById(R.id.weapon_gridweapontype);
        TextView weapTraitTextView = (TextView) convertView.findViewById(R.id.weapon_gridweapontrait);
        TextView weapPropertyTextView = (TextView) convertView.findViewById(R.id.weapon_gridweaponproperty);

        weapon e = new weapon();
        e = weaponlist.get(position);
        nameTextView.setText("Name: " + String.valueOf(e.getName()));
        damageTextView.setText("Armor Class: " + String.valueOf(e.getDamage()));
        dmgTypeTextView.setText("Hit Points: " + String.valueOf(e.getDamageType()));
        weapTypeTextView.setText("Experience: " + String.valueOf(e.getWeaponType()));
        weapTraitTextView.setText("Experience: " + String.valueOf(e.getTraits()));
        weapPropertyTextView.setText("Experience: " + String.valueOf(e.getProperty()));

        return convertView;
    }
}
