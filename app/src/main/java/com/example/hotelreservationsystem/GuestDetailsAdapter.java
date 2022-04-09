package com.example.hotelreservationsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GuestDetailsAdapter extends RecyclerView.Adapter<GuestDetailsAdapter.ViewHolder> {

    private String numberofGuests;
    private LayoutInflater layoutInflater;
    private ItemClickListener clickListener;

    GuestDetailsAdapter(Context context, String numberOfGuests) {
        this.layoutInflater = LayoutInflater.from(context);
        this.numberofGuests = numberOfGuests;
    }

    @NonNull
    @Override
    public GuestDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.hotel_guest_details_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestDetailsAdapter.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return Integer.parseInt(numberofGuests);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
