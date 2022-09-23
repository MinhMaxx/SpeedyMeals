package com.example.speedymeals.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.speedymeals.R;
import com.example.speedymeals.model.CommonCart;


public class fragment_cart extends Fragment {
    private CommonCart mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        mViewModel = new ViewModelProvider(getActivity(), (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(CommonCart.class);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.cartView);
        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        return view;
    }

    private class cartVHolder extends RecyclerView.ViewHolder{

        public cartVHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}