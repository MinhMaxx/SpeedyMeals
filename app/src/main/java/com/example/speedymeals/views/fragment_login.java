package com.example.speedymeals.views;

import static android.graphics.Color.parseColor;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.speedymeals.R;
import com.example.speedymeals.database.DBManager;
import com.example.speedymeals.model.CommonUser;
import com.example.speedymeals.model.User;
import com.google.android.material.snackbar.Snackbar;

public class fragment_login extends Fragment
{
    private ViewGroup parent;
    private EditText email;
    private EditText password;
    private Button loginButton;
    private Button registerButton;
    private DBManager dbManager;
    private CommonUser userData;

    public fragment_login() {

    }

    // TODO: Rename and change types and number of parameters
    public static fragment_login newInstance() {return new fragment_login();}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parent = container;
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        loginButton = view.findViewById(R.id.loginButton);
        registerButton = view.findViewById(R.id.orderButton);

        View snakeBarView = getActivity().findViewById(android.R.id.content);
        Snackbar mySnackbar = Snackbar.make(snakeBarView, "", 2000);
        mySnackbar.setTextColor(0xFFFFFFFF);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!email.getText().toString().isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    if(!password.getText().toString().isEmpty()){
                        dbManager = DBManager.getInstance(null);
                        User newUser = dbManager.checkUser(email.getText().toString().toLowerCase(),password.getText().toString());
                        if(newUser != null){
                            mySnackbar.setText("Login Sucessful");
                            mySnackbar.setBackgroundTint(parseColor("#388E3C"));
                            mySnackbar.show();
                            userData = new ViewModelProvider(getActivity()).get(CommonUser.class);
                            userData.setUser(newUser);
                            fragment_profile newFrag = new fragment_profile();
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.mainMenuView,newFrag)
                                    .addToBackStack(null)
                                    .commit();
                        }
                        else
                        {
                            mySnackbar.setText("Login Unsuccessful, please check your Email and Password!");
                            mySnackbar.setBackgroundTint(0xFFD0342C);
                            mySnackbar.show();
                        }
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

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_register newFrag = new fragment_register();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainMenuView,newFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}
