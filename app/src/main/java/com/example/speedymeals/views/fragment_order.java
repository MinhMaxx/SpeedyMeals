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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.speedymeals.R;
import com.example.speedymeals.database.DBManager;
import com.example.speedymeals.model.CommonCart;
import com.example.speedymeals.model.CommonUser;
import com.example.speedymeals.model.Food;
import com.example.speedymeals.model.Order;
import com.example.speedymeals.model.RestaurantList;

import java.util.ArrayList;
import java.util.List;


public class fragment_order extends Fragment {
    private ViewGroup parent;
    private DBManager dbManager;
    private CommonUser userData;
    private List<Order> orderList;

    public static fragment_order newInstance() { return new fragment_order();}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle bundle) {
        parent = container;
        View view = inflater.inflate(R.layout.fragment_order_list, container,false);
        userData = new ViewModelProvider(getActivity()).get(CommonUser.class);
        dbManager = DBManager.getInstance(null);
        orderList = new ArrayList<>();
        orderList= dbManager.readOrderOfUser(userData.getUser().getId());

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.orderRecycler);
        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        OrderAdapter adapter = new OrderAdapter();
        rv.setAdapter(adapter);

        return view;
    }

    public class OrderAdapter extends RecyclerView.Adapter<OrderVHolder>{

        @NonNull
        @Override
        public OrderVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.fragment_order, parent, false);
            OrderVHolder myOrderVHolder = new OrderVHolder(view);
            return myOrderVHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull OrderVHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return orderList.size();
        }
    }

    public class OrderVHolder extends RecyclerView.ViewHolder{
        public TextView orderTitleDate, addressTitle,foodNameList, restaurantNameList, foodListNum, foodListPrice, totalTitlePrice;

        public OrderVHolder(View itemView){
            super(itemView);
            orderTitleDate = itemView.findViewById(R.id.orderTitleDate);
            addressTitle = itemView.findViewById(R.id.addressTitle);
            foodNameList = itemView.findViewById(R.id.foodNameList);
            restaurantNameList = itemView.findViewById(R.id.restaurantNameList);
            foodListNum = itemView.findViewById(R.id.foodListNum);
            foodListPrice = itemView.findViewById(R.id.foodListPrice);
            totalTitlePrice = itemView.findViewById(R.id.totalTitlePrice);
        }

        public void bind(int pos)
        {
            Order order = orderList.get(pos);
            orderTitleDate.setText(order.getDate());
            addressTitle.setText(order.getAddress());
            foodNameList.setText(order.getFoodName());
            restaurantNameList.setText(order.getRestaurantName());
            foodListNum.setText(order.getFoodNumber());
            foodListPrice.setText(order.getFoodPrice());
            totalTitlePrice.setText(order.getTotalCost());
        }
    }
}