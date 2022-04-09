package com.example.hotelreservationsystem;

import android.view.View;


// interface created for which view and what position user should click, so you can override
public interface ItemClickListener {

    public void onClick(View view, int position);
}
