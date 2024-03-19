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

    private OnItemClickListener listener;

    public AdapterRestaurant(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public interface OnItemClickListener {
        void onItemClick(Restaurant restaurant);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_restaurant_list, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);

        holder.nameTextView.setText(restaurant.getName());
        Drawable imageDrawable = holder.itemView.getContext().getResources().getDrawable(R.drawable.test_image);
        holder.imageImageView.setImageDrawable(imageDrawable);
        holder.addressTextView.setText(restaurant.getAddress());
        holder.lastCommentTextView.setText('"' + restaurant.getCommentList().get(restaurant.getCommentList().size() - 1).getText() + '"');
        holder.averagePriceTextView.setText(restaurant.getAveragePrice().toString());
        holder.rateTextView.setText(restaurant.getRate().getCalculatedRate().toString());
        holder.typeTextView.setText(restaurant.getType().getLabel());

    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        ImageView imageImageView;
        TextView addressTextView;
        TextView lastCommentTextView;
        TextView typeTextView;
        TextView averagePriceTextView;
        TextView rateTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.name_restaurant);
            imageImageView = itemView.findViewById(R.id.image_restaurant);
            addressTextView = itemView.findViewById(R.id.address_restaurant);
            lastCommentTextView = itemView.findViewById(R.id.last_comment_restaurant);
            typeTextView = itemView.findViewById(R.id.type_restaurant);
            averagePriceTextView = itemView.findViewById(R.id.averagePrice_restaurant);
            rateTextView = itemView.findViewById(R.id.rate_restaurant);

            // Ecoute d'un clic sur une carte
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(restaurants.get(position));
                        }
                    }
                }
            });
        }
    }

}

