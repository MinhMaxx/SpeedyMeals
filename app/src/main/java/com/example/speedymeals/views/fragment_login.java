package com.example.speedymeals.views;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.example.speedymeals.R;
import com.google.android.material.snackbar.Snackbar;

public class fragment_login extends Fragment
{
    private ViewGroup parent;
    private EditText email;
    private EditText password;
    private Button loginButton;
    private Button registerButton;


    public fragment_login() {

    }

    // TODO: Rename and change types and number of parameters
    public static fragment_profile newInstance() {return new fragment_profile();}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parent = container;
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        loginButton = view.findViewById(R.id.loginButton);
        registerButton = view.findViewById(R.id.registerButton);

        View snakeBarView = getActivity().findViewById(android.R.id.content);
        Snackbar mySnackbar = Snackbar.make(snakeBarView, "", 1000);
        mySnackbar.setTextColor(0xFFFFFFFF);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!email.getText().toString().isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    if(!password.getText().toString().isEmpty()){

                    }
                    else {
                        mySnackbar.setText("Invalid Password!");
                        mySnackbar.setBackgroundTint(0xFFD0342C);
                        mySnackbar.show();
                    }
                } else {
                    mySnackbar.setText("Invalid Email!");
                    mySnackbar.setBackgroundTint(0xFFD0342C);
                    mySnackbar.show();
                }
            }
        });

        return view;
    }
}
