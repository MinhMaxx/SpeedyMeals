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

import com.example.speedymeals.R;
import com.example.speedymeals.database.DBManager;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

public class fragment_register extends Fragment
{
    private ViewGroup parent;
    private EditText email;
    private EditText password;
    private EditText rePassword;
    private EditText name;
    private EditText address;
    private Button loginButton;
    private Button registerButton;
    private DBManager dbManager;
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public fragment_register() {

    }

    // TODO: Rename and change types and number of parameters
    public static fragment_register newInstance() {return new fragment_register();}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parent = container;
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        rePassword = view.findViewById(R.id.rePassword);
        name = view.findViewById(R.id.name);
        address = view.findViewById(R.id.address);
        loginButton = view.findViewById(R.id.loginButton);
        registerButton = view.findViewById(R.id.orderButton);

        View snakeBarView = getActivity().findViewById(android.R.id.content);
        Snackbar mySnackbar = Snackbar.make(snakeBarView, "", 2000);
        mySnackbar.setTextColor(0xFFFFFFFF);
        mySnackbar.setBackgroundTint(0xFFD0342C);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!email.getText().toString().isEmpty()&& !password.getText().toString().isEmpty()
                        && !name.getText().toString().isEmpty() && !address.getText().toString().isEmpty()) {
                    if (Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                        if(password.getText().toString().equals(rePassword.getText().toString())){
                            if(pattern.matcher(password.getText().toString()).matches()){
                                dbManager = DBManager.getInstance(null);
                                if(dbManager.addUser(email.getText().toString(),password.getText().toString(),name.getText().toString(),address.getText().toString())){
                                    mySnackbar.setText("Register Sucessful");
                                    mySnackbar.setBackgroundTint(parseColor("#388E3C"));
                                    mySnackbar.show();
                                    fragment_login newFrag = new fragment_login();
                                    getActivity().getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.mainMenuView,newFrag)
                                            .addToBackStack(null)
                                            .commit();
                                }
                                else
                                {
                                    mySnackbar.setText("Register Unsuccessful, Email have already been registered");
                                    mySnackbar.show();
                                }
                            }
                            else {
                                mySnackbar.setText("Password must contain 8-20 characters, a number, a lower case, a upper case");
                                mySnackbar.show();
                            }
                        }
                        else {
                            mySnackbar.setText("Passwords does not match!");
                            mySnackbar.show();
                        }
                    }
                    else{
                        mySnackbar.setText("Invalid Email!");
                        mySnackbar.show();
                    }
                } else {
                    mySnackbar.setText("Please fill in all the fields");
                    mySnackbar.show();
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_login newFrag = new fragment_login();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainMenuView,newFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}
