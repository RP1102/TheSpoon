package com.example.thespoon;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.thespoon.Entity.Restaurant;

import java.util.List;

public class AdapterRestaurant extends RecyclerView.Adapter<AdapterRestaurant.ViewHolder> {

    private List<Restaurant> restaurants;

    public AdapterRestaurant(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_restaurant_list, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);

        System.out.println(restaurant);
        holder.nameTextView.setText(restaurant.getName());
        Drawable imageDrawable = holder.itemView.getContext().getResources().getDrawable(R.drawable.restaurant);
        holder.imageImageView.setImageDrawable(imageDrawable);
        holder.addressTextView.setText(restaurant.getAddress());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        ImageView imageImageView;
        TextView addressTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.name_restaurant);
            imageImageView = itemView.findViewById(R.id.image_restaurant);
            addressTextView = itemView.findViewById(R.id.address_restaurant);
        }
    }

}

