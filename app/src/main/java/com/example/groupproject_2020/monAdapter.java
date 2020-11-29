package com.example.groupproject_2020;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class monAdapter extends BaseAdapter {
    Context context;
    ArrayList<monster> monlist;
    private static LayoutInflater inflater = null;

    public monAdapter(Context context, ArrayList<monster> empList) {
        this.context = context;
        this.monlist = empList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return monlist.size();
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
            convertView = inflater.inflate(R.layout.mon_gridlayout, null);

        TextView nameTextView = (TextView) convertView.findViewById(R.id.mon_gridname);
        TextView armorCLassTextView = (TextView) convertView.findViewById(R.id.mon_gridarmorClass);
        TextView hpTextView = (TextView) convertView.findViewById(R.id.mon_gridhp);
        TextView expTextView = (TextView) convertView.findViewById(R.id.mon_gridexp);

        monster e = new monster();
        e = monlist.get(position);
        nameTextView.setText("Name: " + String.valueOf(e.getName()));
        armorCLassTextView.setText("Armor Class: " + String.valueOf(e.getArmorclass()));
        hpTextView.setText("Hit Points: " + String.valueOf(e.getHitpoints()));
        expTextView.setText("Experience: " + String.valueOf(e.getExp()));

        return convertView;
    }

}
