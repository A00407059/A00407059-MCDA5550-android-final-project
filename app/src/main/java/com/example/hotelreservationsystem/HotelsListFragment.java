package com.example.hotelreservationsystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

//Implements click listner in order to tell fragment to use click listner
public class HotelsListFragment extends Fragment implements ItemClickListener{
    View view;
    TextView headingTextView;
    ProgressBar progressBar;
    List<HotelListData> userListResponseData;
    List<HotelListData> userHandCodingData;


//inflate hotel list fragment
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_list_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        headingTextView = view.findViewById(R.id.heading_text_view);
        progressBar = view.findViewById(R.id.progress_bar);

        String checkInDate = getArguments().getString("check in date");
        String checkOutDate = getArguments().getString("check out date");
        String numberOfGuests = getArguments().getString("number of guests");
        String nameOfGuest = getArguments().getString("name of guest");

        //Set up the header
        headingTextView.setText("Welcome " + nameOfGuest +
                "\n You selected " + numberOfGuests + " guests staying from " + checkInDate +
                " to " + checkOutDate + "\n You can see the available hotels");

        //ArrayList<HotelListData> hotelListData = initHotelListData();
        //userHandCodingData = initHotelListData();
        //RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), userHandCodingData);
        //recyclerView.setAdapter(hotelListAdapter);
        //hotelListAdapter.setClickListener(this);
        getHotelsListsData();

    }

    public ArrayList<HotelListData> initHotelListData() {
        ArrayList<HotelListData> list = new ArrayList<>();

        list.add(new HotelListData("Halifax Regional Hotel", "2000", "true"));
        list.add(new HotelListData("Hotel Pearl", "500", "false"));
        list.add(new HotelListData("Hotel Amano", "800", "true"));
        list.add(new HotelListData("San Jones", "250", "false"));
        list.add(new HotelListData("Halifax Regional Hotel", "2000", "true"));
        list.add(new HotelListData("Hotel Pearl", "500", "false"));
        list.add(new HotelListData("Hotel Amano", "800", "true"));
        list.add(new HotelListData("San Jones", "250", "false"));

        return list;
    }


    private void getHotelsListsData() {
        progressBar.setVisibility(View.VISIBLE);
        Api.getClient().getHotelsLists(new Callback<List<HotelListData>>() {
            @Override
            public void success(List<HotelListData> userListResponses, Response response) {
                // in this method we will get the response from API
                userListResponseData = userListResponses;


                // Set up the RecyclerView
                setupRecyclerView();
            }

            @Override
            public void failure(RetrofitError error) {
                // if error occurs in network transaction then we can get the error in this method.
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void setupRecyclerView() {
        progressBar.setVisibility(View.GONE);
        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), userListResponseData);
        recyclerView.setAdapter(hotelListAdapter);

        //Bind the click listener
        hotelListAdapter.setClickListener(this);
    }

    @Override
    public void onClick(View view, int position) {
        HotelListData hotelListData = userListResponseData.get(position);

        // Get data from each view holder(layout)
        String hotelName = hotelListData.getHotel_name();
        String price = hotelListData.getPrice();
        String availability = hotelListData.getAvailability();
        String numberOfGuests = getArguments().getString("number of guests");
        String checkInDate = getArguments().getString("check in date");
        String checkOutDate = getArguments().getString("check out date");

        //Pass the data as bundle to Hotel Detail Fragment
        Bundle bundle = new Bundle();
        bundle.putString("hotel name", hotelName);
        bundle.putString("hotel price", price);
        bundle.putString("hotel availability", availability);
        bundle.putString("number of guests", numberOfGuests);
        bundle.putString("check in date", checkInDate);
        bundle.putString("check out date", checkOutDate);

        HotelGuestDetailsFragment hotelGuestDetailsFragment = new HotelGuestDetailsFragment();
        hotelGuestDetailsFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.remove(HotelsListFragment.this);
        fragmentTransaction.replace(R.id.main_layout, hotelGuestDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();

    }

}


