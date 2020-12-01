package com.example.groupproject_2020;

import android.content.Context;

import androidx.appcompat.widget.AppCompatButton;

public class pcButton extends AppCompatButton {
    private character pc;

    public pcButton(Context context, character newPc){
        super( context );
        pc = newPc;
    }

    public int getID(){
        return pc.getId();
    }
}
