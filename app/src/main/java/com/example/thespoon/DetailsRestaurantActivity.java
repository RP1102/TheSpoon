package com.example.thespoon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
                // Récupérez le TextView pour le nom du restaurant
                TextView restaurantNameTextView = findViewById(R.id.restaurant_name);
                // Définissez le nom du restaurant dans le TextView
                restaurantNameTextView.setText(restaurant.getName());
            }
        }
    }
}
