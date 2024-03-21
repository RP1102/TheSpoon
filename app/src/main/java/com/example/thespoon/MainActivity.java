package com.example.thespoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thespoon.DatabaseAccess.FirebaseAccess;
import com.example.thespoon.Entity.Restaurant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {

    List<Restaurant> restaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchRestaurants();

    }

    private void fetchRestaurants() {
        FirebaseAccess firebaseAccess = FirebaseAccess.getInstance();
        DatabaseReference databaseReference = firebaseAccess.getDatabase().getReference("restaurants");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Restaurant> restaurants = new ArrayList<>();
                for (DataSnapshot restaurantSnapshot: dataSnapshot.getChildren()) {
                    Restaurant restaurant = restaurantSnapshot.getValue(Restaurant.class);
                    restaurants.add(restaurant);
                }

                AdapterRestaurant adapter = new AdapterRestaurant(restaurants);
                RecyclerView recyclerView = findViewById(R.id.list_restaurants);
                recyclerView.setAdapter(adapter);

                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                adapter.setOnItemClickListener(new AdapterRestaurant.OnItemClickListener() {
                    @Override
                    public void onItemClick(Restaurant restaurant) {

                        Intent intent = new Intent(MainActivity.this, DetailsRestaurantActivity.class);
                        intent.putExtra("restaurant", restaurant);
                        startActivity(intent);

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}