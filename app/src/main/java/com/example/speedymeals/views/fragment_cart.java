package com.example.speedymeals.views;

import android.app.AlertDialog;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.speedymeals.R;
import com.example.speedymeals.model.CommonCart;
import com.example.speedymeals.model.Food;
import com.example.speedymeals.model.Order;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class fragment_cart extends Fragment {
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private CommonCart mViewModel;
    private TextView totalPrice;
    private TextView noOfItem, foodname, pricetext;
    private ImageView foodPic;
    private Button plus,minus,change, checkout;
    cartAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        mViewModel = new ViewModelProvider(getActivity(), (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(CommonCart.class);

        totalPrice = view.findViewById(R.id.total);
        checkout = view.findViewById(R.id.checkOut);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mViewModel.cartSize() != 0)
                {
                    checkout();
                }
            }
        });
        totalPrice.setText(String.valueOf(mViewModel.totalPrice()));
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.cartView);
        rv.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        adapter = new cartAdapter();
        rv.setAdapter(adapter);

        return view;
    }

    public class cartAdapter extends RecyclerView.Adapter<cartVHolder>
    {

        @NonNull
        @Override
        public cartVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.food_restaurant_listitem, parent, false);
            cartVHolder myCartHolder = new cartVHolder(view);
            return myCartHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull cartVHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return mViewModel.cartSize();
        }
    }

    private class cartVHolder extends RecyclerView.ViewHolder{
        public ImageView foodPic;
        public TextView foodName, price, description, noOfItem,noOrder;
        public cartVHolder(@NonNull View itemView) {
            super(itemView);
            foodPic = itemView.findViewById(R.id.foodImg);
            foodName = itemView.findViewById(R.id.foodName);
            price = itemView.findViewById(R.id.price);
            description = itemView.findViewById(R.id.desc);
            noOfItem = itemView.findViewById(R.id.restName);
            noOrder= itemView.findViewById(R.id.noOrder);
            //reusing layout, applying different text
        }

        public void bind(int pos)
        {
            Food inFood = mViewModel.getFoods(pos);
            foodPic.setImageResource(inFood.getProfilePictureID());
            foodName.setText(inFood.getName());
            price.setText(String.valueOf(inFood.getPrice()));
            description.setText(inFood.getDescription());
            noOfItem.setText(String.valueOf(mViewModel.getAmount(pos)));
            noOrder.setText("x ");

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    createNewFoodDialog(inFood, pos, noOfItem.getText().toString());
                }
            });
        }
    }

    public void createNewFoodDialog(Food inFood, int pos, String noItem){
        dialogBuilder = new AlertDialog.Builder(/*parent.getContext()*/this.getContext());

        final View foodPopup = getLayoutInflater().inflate(R.layout.foodpopup, null);
        noOfItem = foodPopup.findViewById(R.id.popNoText);
        foodname = foodPopup.findViewById(R.id.popfoodText);
        pricetext = foodPopup.findViewById(R.id.poppricetext);
        foodPic = foodPopup.findViewById(R.id.popfoodIcon);
        plus = foodPopup.findViewById(R.id.plus);
        minus = foodPopup.findViewById(R.id.minus);
        change = foodPopup.findViewById(R.id.add);

        foodname.setText(inFood.getName());
        pricetext.setText("$" + String.valueOf(inFood.getPrice()));
        foodPic.setImageResource(inFood.getProfilePictureID());
        noOfItem.setText(noItem);
        dialogBuilder.setView(foodPopup);
        change.setText("Change");

        dialog = dialogBuilder.create();
        dialog.show();

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(String.valueOf(noOfItem.getText())) !=0)
                {
                    mViewModel.setNoAmount(pos, Integer.valueOf(String.valueOf(noOfItem.getText())));
                    totalPrice.setText(String.valueOf(mViewModel.totalPrice()));
                    adapter.notifyItemChanged(pos);}
                else if(Integer.parseInt(String.valueOf(noOfItem.getText())) == 0)
                {
                    mViewModel.setNoAmount(pos, Integer.valueOf(String.valueOf(noOfItem.getText())));
                    totalPrice.setText(String.valueOf(mViewModel.totalPrice()));
                    adapter.notifyItemRemoved(pos);
                    adapter.notifyItemRangeChanged(pos, mViewModel.cartSize());

                }
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

    private void checkout()
    {
        ArrayList<Order> listOfOrders;
        Food cFood;
        Integer nOfFood;
        //TODO get user ID
        for(int ii = 0; ii < mViewModel.cartSize(); ii ++)
        {
            cFood = mViewModel.getFoods(ii);
            nOfFood = mViewModel.getAmount(ii);
            Order nOrder = new Order(cFood.getId(),
                    1/*TODO Add user ID*/,
                    "home"/*TODO AddUserAddress*/ ,
                    /*TODO Get Rest Name*/String.valueOf(cFood.getRestaurantID()).split(" "),
                    cFood.getName().split(" "),
                    /*TODO may need to convert nOfFood to int*/String.valueOf(nOfFood).split(" "),
                    /*TODO may need to change price to double*/String.valueOf(cFood.getPrice()).split(" ")
                    /*TODO*/, new Date(), (cFood.getPrice()*nOfFood));
            //adapter.notifyItemRemoved(ii);
        }

        adapter.notifyItemRangeRemoved(0, mViewModel.cartSize());
        mViewModel.clearCart();


        totalPrice.setText(String.valueOf(mViewModel.totalPrice()));

    }

}