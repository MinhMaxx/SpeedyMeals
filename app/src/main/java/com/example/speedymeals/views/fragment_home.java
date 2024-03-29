package com.example.speedymeals.views;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.speedymeals.R;
import com.example.speedymeals.model.*;

import java.util.ArrayList;


public class fragment_home extends Fragment {
    private ViewGroup parent;

    private ArrayList<Food> data;
    private RestaurantList restList;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView noOfItem, foodname, pricetext;
    private ImageView foodPic;
    private Button plus,minus,addtocart;
    private boolean isTablet;

    private CommonCart mViewModel;

    public static fragment_home newInstance() { return new fragment_home();}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle bundle) {
        parent = container;
        data = getArguments().getParcelableArrayList("fodList");
        restList = getArguments().getParcelable("restList");
        isTablet = getArguments().getBoolean("isTablet");
        View view = inflater.inflate(R.layout.fragment_home, container,false);

        mViewModel = new ViewModelProvider(getActivity(), (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(CommonCart.class);


        RecyclerView rv = (RecyclerView) view.findViewById(R.id.homeRecycler);
        if(!isTablet)
            rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        else
            rv.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));
        HomeAdapter adapter = new HomeAdapter();
        rv.setAdapter(adapter);

        return view;
    }

    public class HomeAdapter extends RecyclerView.Adapter<foodVHolder>{

        @NonNull
        @Override
        public foodVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.food_restaurant_listitem, parent, false);
            foodVHolder myFoodVHolder = new foodVHolder(view);
            return myFoodVHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull foodVHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    public class foodVHolder extends RecyclerView.ViewHolder{
        public ImageView foodImg;
        public TextView restName, foodName, price, desc,noOrder;

        public foodVHolder(View itemView){
            super(itemView);
            foodImg = itemView.findViewById(R.id.foodImg);
            restName = itemView.findViewById(R.id.restName);
            foodName = itemView.findViewById(R.id.foodName);
            price = itemView.findViewById(R.id.price);
            desc = itemView.findViewById(R.id.desc);
            noOrder = itemView.findViewById(R.id.noOrder);
        }

        public void bind(int pos)
        {
            Food nFood = data.get(pos);

            foodImg.setImageResource(nFood.getProfilePictureID());
            restName.setText(restList.get(nFood.getRestaurantID()-1).getName());
            foodName.setText(nFood.getName());
            price.setText(String.valueOf(nFood.getPrice()));
            desc.setText(nFood.getDescription());
            noOrder.setText("");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    createNewFoodDialog(nFood);
                }
            });

        }
    }

    public void createNewFoodDialog(Food inFood){
        dialogBuilder = new AlertDialog.Builder(/*parent.getContext()*/this.getContext());

        final View foodPopup = getLayoutInflater().inflate(R.layout.food_popup, null);
        noOfItem = foodPopup.findViewById(R.id.popNoText);
        foodname = foodPopup.findViewById(R.id.popfoodText);
        pricetext = foodPopup.findViewById(R.id.poppricetext);
        foodPic = foodPopup.findViewById(R.id.popfoodIcon);
        plus = foodPopup.findViewById(R.id.plus);
        minus = foodPopup.findViewById(R.id.minus);
        addtocart = foodPopup.findViewById(R.id.add);

        foodname.setText(inFood.getName());
        pricetext.setText("$" + String.valueOf(inFood.getPrice()));
        foodPic.setImageResource(inFood.getProfilePictureID());

        dialogBuilder.setView(foodPopup);
        dialog = dialogBuilder.create();
        dialog.show();

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(String.valueOf(noOfItem.getText())) !=0)
                    mViewModel.addtoList(inFood, Integer.valueOf(String.valueOf(noOfItem.getText())));
                dialog.dismiss();
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amount = Integer.parseInt((String) noOfItem.getText());
                amount ++;
                noOfItem.setText(String.valueOf(amount));

            }
        });

        minus.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int amount = Integer.parseInt((String) noOfItem.getText());
                    if(amount != 0)
                        amount--;
                noOfItem.setText(String.valueOf(amount));
            }
        });

    }
}