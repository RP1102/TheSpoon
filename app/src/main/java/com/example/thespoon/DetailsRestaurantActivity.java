package com.example.thespoon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

                View cardRestaurantView = findViewById(R.id.card_restaurant);

                ////
                TextView restaurantNameTextView = cardRestaurantView.findViewById(R.id.name_restaurant);

                restaurantNameTextView.setText(restaurant.getName());
            }
        }


    }
}
