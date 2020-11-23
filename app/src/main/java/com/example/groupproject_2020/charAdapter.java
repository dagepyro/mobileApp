package com.example.groupproject_2020;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class charAdapter extends BaseAdapter {
    Context context;
    ArrayList<character> charlist;
    private static LayoutInflater inflater = null;

    public charAdapter(Context context, ArrayList<character> empList) {
        this.context = context;
        this.charlist = empList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return charlist.size();
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
            convertView = inflater.inflate(R.layout.char_gridlayout, null);

        TextView nameTextView = (TextView) convertView.findViewById(R.id.char_gridname);
        TextView alignTextView = (TextView) convertView.findViewById(R.id.char_gridalign);
        TextView raceTextView = (TextView) convertView.findViewById(R.id.char_gridrace);
        TextView classTextView = (TextView) convertView.findViewById(R.id.char_gridclass);

        character e = new character();
        e = charlist.get(position);
        nameTextView.setText("Name: " + String.valueOf(e.getName()));
        alignTextView.setText("Alignment: " + String.valueOf(e.getAlignment()));
        raceTextView.setText("Race: " + String.valueOf(e.getRace()));
        classTextView.setText("Class: " + String.valueOf(e.getCharclass()));


        return convertView;
    }

}