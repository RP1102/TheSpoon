package com.example.thespoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thespoon.DatabaseAccess.FirebaseAccess;
import com.example.thespoon.Entity.Comment;
import com.example.thespoon.Entity.Restaurant;
import com.example.thespoon.Entity.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class MainActivity extends AppCompatActivity {

    List<Restaurant> restaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchLastComments();
    }

    private void fetchRestaurants(List<Comment> commentList) {
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


                AdapterRestaurant adapter = new AdapterRestaurant(restaurants, commentList);
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

    public void fetchLastComments(){

        DatabaseReference commentsRef = FirebaseDatabase.getInstance().getReference("comments");
        commentsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Comment> comments = new ArrayList<>();
                for (DataSnapshot commentSnapshot: dataSnapshot.getChildren()) {
                    Comment comment = commentSnapshot.getValue(Comment.class);
                    comments.add(comment);
                }
                fetchRestaurants(comments);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Gérer les erreurs de lecture de la base de données ici
            }
        });
    }
}