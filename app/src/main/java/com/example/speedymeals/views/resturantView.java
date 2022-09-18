package com.example.speedymeals.views;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.speedymeals.R;

public class resturantView extends Fragment {

    private ResturantViewViewModel mViewModel;

    public static resturantView newInstance() {
        return new resturantView();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_resturant_view, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.)
    }

}