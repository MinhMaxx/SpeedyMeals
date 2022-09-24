package com.example.speedymeals.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.speedymeals.R;
import com.example.speedymeals.model.CommonUser;
import com.example.speedymeals.model.User;


public class fragment_profile extends Fragment {
    private ViewGroup parent;
    private CommonUser data;
    private TextView titleHello;


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
        data = new ViewModelProvider(getActivity()).get(CommonUser.class);
        titleHello.setText("Hello "+data.getUser().getName()+ " !");


        // Inflate the layout for this fragment
        return view;
    }
}