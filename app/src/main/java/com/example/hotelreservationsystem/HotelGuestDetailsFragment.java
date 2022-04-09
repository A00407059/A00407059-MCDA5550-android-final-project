package com.example.hotelreservationsystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HotelGuestDetailsFragment extends Fragment {
    View view;
    String guestName, genderGuest;
    EditText guestNameEditText, guestsGenderEditText ;
    Button submitButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_guest_details_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView hotelRecapTextView = view.findViewById(R.id.hotel_recap_text_view);
        guestNameEditText = view.findViewById(R.id.guests_name_edit_text);
        guestsGenderEditText = view.findViewById(R.id.guests_gender_edit_text);
        submitButton = view.findViewById(R.id.confirm_guest_search_button);

        String hotelName = getArguments().getString("hotel name");
        String hotelPrice = getArguments().getString("hotel price");
        String hotelAvailability = getArguments().getString("hotel availability");
        String numberOfGuests = getArguments().getString("number of guests");
        String checkInDate = getArguments().getString("check in date");
        String checkOutDate = getArguments().getString("check out date");

        hotelRecapTextView.setText("Selected Hotel: " +hotelName+ "\n" +
                "Price per night: $" +hotelPrice+ " CAD" +
                "\n Number of guests: " +numberOfGuests+
                "\n Check-In date: " +checkInDate + " \n Check-Out date: "+checkOutDate+
                "\n\n Please enter the details of Guest");


        List<GuestDetailsData> guestDetailsDataList;
        //userHandCodingData = initHotelListData();


        RecyclerView recyclerView = view.findViewById(R.id.hotel_recap_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        GuestDetailsAdapter guestDetailsAdapter = new GuestDetailsAdapter(getActivity(), numberOfGuests);
        recyclerView.setAdapter(guestDetailsAdapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String hotelName = getArguments().getString("hotel name");
                String checkInDate = getArguments().getString("check in date");
                String checkOutDate = getArguments().getString("check out date");
                //String guestName = guestNameEditText.getText().toString();
                //String genderGuest = guestsGenderEditText.getText().toString();


                Bundle bundle = new Bundle();
                bundle.putString("hotel name", hotelName);
                bundle.putString("check in date", checkInDate);
                bundle.putString("check out date", checkOutDate);
                bundle.putString("name of guest", guestName);
                bundle.putString("gender of guest", genderGuest);


                ConfirmationFragment confirmationFragment = new ConfirmationFragment();
                confirmationFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_layout, confirmationFragment);
                fragmentTransaction.remove(HotelGuestDetailsFragment.this);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
    }




}
