package com.example.thespoon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thespoon.Entity.Restaurant;

public class DetailsRestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_restaurant);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("restaurant")) {
            Restaurant restaurant = intent.getParcelableExtra("restaurant");
            if (restaurant != null) {
                // Initialize Recycler view for comments
                RecyclerView recyclerView = findViewById(R.id.list_comments);
                AdapterComment adapter = new AdapterComment(restaurant.getCommentList());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                View restaurantMainInfo = findViewById(R.id.restaurant_main_info);

                //Linking view elements to selected restaurant data
                TextView nameTextView = restaurantMainInfo.findViewById(R.id.name_restaurant);
                ImageView imageImageView = restaurantMainInfo.findViewById(R.id.image_restaurant);
                TextView addressTextView = restaurantMainInfo.findViewById(R.id.address_restaurant);
                TextView typeTextView = restaurantMainInfo.findViewById(R.id.type_restaurant);
                TextView averagePriceTextView = restaurantMainInfo.findViewById(R.id.averagePrice_restaurant);
                TextView rateTextView = restaurantMainInfo.findViewById(R.id.rate_restaurant);
                TextView lastCommentTextView = restaurantMainInfo.findViewById(R.id.last_comment_restaurant);


                nameTextView.setText(restaurant.getName());
                imageImageView.setImageDrawable(getDrawable(R.drawable.restaurant));
                addressTextView.setText(restaurant.getAddress());
                typeTextView.setText(restaurant.getType().getLabel());
                averagePriceTextView.setText(restaurant.getAveragePrice().toString());
                rateTextView.setText(restaurant.getRate().getCalculatedRate().toString());
                lastCommentTextView.setText('"' + restaurant.getCommentList().get(restaurant.getCommentList().size() - 1).getText() + '"');





            }
        }


    }
}
