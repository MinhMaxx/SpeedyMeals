package com.example.speedymeals.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.speedymeals.R;
import com.example.speedymeals.database.DBManager;
import com.example.speedymeals.model.CommonUser;
import com.example.speedymeals.model.Order;
import com.example.speedymeals.model.User;

import java.util.ArrayList;
import java.util.List;


public class fragment_profile extends Fragment {
    private ViewGroup parent;
    private CommonUser userData;
    private TextView titleHello;
    private Button  orderButton,logoutButton;
    private DBManager dbManager;
    private List<Order> orderList;

    public fragment_profile() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        parent = container;
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        titleHello = view.findViewById(R.id.titleHello);
        orderButton = view.findViewById(R.id.orderButton);
        logoutButton = view.findViewById(R.id.logoutButton);
        userData = new ViewModelProvider(getActivity()).get(CommonUser.class);
        titleHello.setText("Hello "+userData.getUser().getName()+ " !");
        dbManager = DBManager.getInstance(null);
        orderList = new ArrayList<>();

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_order newFrag = new fragment_order();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainMenuView,newFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_login newFrag = new fragment_login();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainMenuView,newFrag)
                        .addToBackStack(null)
                        .commit();
                userData.clearUser();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}