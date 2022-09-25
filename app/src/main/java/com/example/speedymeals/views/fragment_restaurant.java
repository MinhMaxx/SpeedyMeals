package com.example.speedymeals.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.speedymeals.R;
import com.example.speedymeals.database.DBManager;
import com.example.speedymeals.model.Restaurant;
import com.example.speedymeals.model.RestaurantList;

import java.nio.channels.Selector;


public class fragment_restaurant extends Fragment {

    private DBManager dbManager;
    private RestaurantList restaurants;
    private boolean isTablet;

    public static fragment_restaurant newInstance() {
        return new fragment_restaurant();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_restaurant_view, container, false);

        dbManager = DBManager.getInstance(getActivity());
        dbManager.open();
        restaurants = new RestaurantList();
        restaurants.load(dbManager.readRestaurant());
        isTablet = getArguments().getBoolean("isTablet");

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.restaurantRecyclerView);
        if(!isTablet)
            rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        else
            rv.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));
        RestaurantAdapter adapter = new RestaurantAdapter();
        rv.setAdapter(adapter);

        return view;
    }

    public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantVHolder> {

        @NonNull
        @Override
        public RestaurantVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.resterant_list_view, parent, false);
            RestaurantVHolder myRestaurantHolder = new RestaurantVHolder(view);
            return myRestaurantHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RestaurantVHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return restaurants.size();
        }
    }

    public class RestaurantVHolder extends RecyclerView.ViewHolder {
        public TextView restName;
        public ImageView restPic;
        public RestaurantVHolder(View itemView) {
            super(itemView);
            restName = itemView.findViewById(R.id.restaurant_text);
            restPic = itemView.findViewById(R.id.restaurant_icon);

        }

        public void bind(int pos)
        {
            Restaurant nRestaurant = restaurants.get(pos);
            restName.setText(nRestaurant.getName());
            restPic.setImageResource(nRestaurant.getProfilePictureID());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragment_foods newFrag = new fragment_foods();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("selectedRest", nRestaurant);
                    bundle.putBoolean("isTablet", isTablet);
                    newFrag.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.mainMenuView,newFrag,"foods")
                            .addToBackStack(null)
                            .commit();
                }
            });
        }

    }
}
