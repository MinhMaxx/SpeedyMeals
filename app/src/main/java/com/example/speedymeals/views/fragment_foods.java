package com.example.speedymeals.views;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.speedymeals.R;
import com.example.speedymeals.model.Food;
import com.example.speedymeals.model.Restaurant;

public class fragment_foods extends Fragment {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView noOfItem, foodname, pricetext, restName;
    private ImageView foodPic, restPic;
    private Button plus,minus,addtocart;

   private Restaurant cRestaurant;
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle bundle) {
         View view = inflater.inflate(R.layout.fragment_foods, container, false);

         //setting up fragment view

        cRestaurant = getArguments().getParcelable("selectedRest");
        restPic = view.findViewById(R.id.restaurantImageView);
        restPic.setImageResource(cRestaurant.getProfilePictureID());
        restName = view.findViewById(R.id.foodRestName);
        restName.setText(cRestaurant.getName());

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.foodlist);
        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        FoodAdapter rAdapter = new FoodAdapter();
        rv.setAdapter(rAdapter);

    return view;
   }

   private class FoodAdapter extends RecyclerView.Adapter<foodVHolder>{

       @NonNull
       @Override
       public foodVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
           View view = layoutInflater.inflate(R.layout.food_restaurant_listitem, parent, false);
           foodVHolder myFoodHolder = new foodVHolder(view);
           return myFoodHolder;
       }

       @Override
       public void onBindViewHolder(@NonNull foodVHolder holder, int position) {
            holder.bind(position);
       }

       @Override
       public int getItemCount() {
           return cRestaurant.size();
       }
   }

   private class foodVHolder extends RecyclerView.ViewHolder {
       private ImageView foodimg;
       private TextView restName, foodName, price, desc;
       public foodVHolder(@NonNull View itemView) {
           super(itemView);
           foodimg = itemView.findViewById(R.id.foodImg);
           restName = itemView.findViewById(R.id.restName);
           foodName = itemView.findViewById(R.id.foodName);
           price = itemView.findViewById(R.id.price);
           desc = itemView.findViewById(R.id.desc);
       }

       public void bind(int pos)
       {
           Food nFood = cRestaurant.getFood(pos);
           foodimg.setImageResource(nFood.getProfilePictureID());
           restName.setText("");
           foodName.setText(nFood.getName());
           price.setText(String.valueOf(nFood.getPrice()));
           desc.setText(nFood.getDescription());

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

        final View foodPopup = getLayoutInflater().inflate(R.layout.foodpopup, null);
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