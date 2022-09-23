package com.example.speedymeals.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.speedymeals.R;
import com.example.speedymeals.model.User;


public class fragment_profile extends Fragment {
    private User user;
    private ViewGroup parent;


    public fragment_profile(User user) {
        this.user = user;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        parent = container;
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView title = view.findViewById(R.id.title);
        title.setText("Hello "+ user.getName());

        // Inflate the layout for this fragment
        return view;
    }
}