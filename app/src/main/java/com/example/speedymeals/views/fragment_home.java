package com.example.speedymeals.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.print.PageRange;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.speedymeals.R;


public class fragment_home extends Fragment {

    public static fragment_home newInstance() { return new fragment_home();}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_home, container,false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.homeRecycler);
        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
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
            return 10;
        }
    }

    public class foodVHolder extends RecyclerView.ViewHolder{
        public ImageView foodImg;
        public TextView restName, foodName, price, desc;

        public foodVHolder(View itemView){
            super(itemView);
            foodImg = itemView.findViewById(R.id.foodImg);
            restName = itemView.findViewById(R.id.restName);
            foodName = itemView.findViewById(R.id.foodName);
            price = itemView.findViewById(R.id.price);
            desc = itemView.findViewById(R.id.desc);
        }

        public void bind(int pos)
        {
            foodImg.setImageResource(R.drawable.pho_bo);
            restName.setText("Restaurant" + pos);
            foodName.setText("Food" + pos);
            price.setText("$" + pos);
            desc.setText("Desc:" + pos);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }
}