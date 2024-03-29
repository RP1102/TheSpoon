package com.example.thespoon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thespoon.Adapter.AdapterComment;
import com.example.thespoon.DatabaseAccess.FirebaseAccess;
import com.example.thespoon.Entity.Comment;
import com.example.thespoon.Entity.Restaurant;
import com.example.thespoon.Entity.User;
import com.example.thespoon.Fragment.AddReviewModalFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DetailsRestaurantActivity extends AppCompatActivity implements FragmentListener {

    DetailsRestaurantActivity detailsRestaurantActivity = this;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_restaurant);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("restaurant")) {
            Restaurant restaurant = intent.getParcelableExtra("restaurant");
            if (restaurant != null) {

                fetchAllWriters(restaurant);
                // Initialize Recycler view for comments
                RecyclerView recyclerView = findViewById(R.id.list_comments);
                AdapterComment adapter = new AdapterComment(null, null);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                View restaurantMainInfo = findViewById(R.id.restaurant_main_info);

                // Linking view elements to selected restaurant data
                TextView nameTextView = restaurantMainInfo.findViewById(R.id.name_restaurant);
                ImageView imageImageView = restaurantMainInfo.findViewById(R.id.image_restaurant);
                TextView addressTextView = restaurantMainInfo.findViewById(R.id.address_restaurant);
                TextView typeTextView = restaurantMainInfo.findViewById(R.id.type_restaurant);
                TextView averagePriceTextView = restaurantMainInfo.findViewById(R.id.averagePrice_restaurant);
                TextView rateTextView = restaurantMainInfo.findViewById(R.id.rate_restaurant);
                TextView descriptionTextView = findViewById(R.id.description_restaurant);

                // Set values to view elements
                nameTextView.setText(restaurant.getName());
                addressTextView.setText(restaurant.getAddress());
                typeTextView.setText(restaurant.getType().getLabel());
                averagePriceTextView.setText(restaurant.getAveragePrice().toString());
                rateTextView.setText(restaurant.getRate().toString());
                descriptionTextView.setText(restaurant.getDescription());


                // Fetch the restaurant image from drawable
                Resources resources = getResources();
                int resourceId = resources.getIdentifier(restaurant.getImage(), "drawable", getPackageName());
                if (resourceId != 0) {
                    Drawable restaurantDrawable = resources.getDrawable(resourceId);
                    imageImageView.setImageDrawable(restaurantDrawable);
                } else {
                    // Case of no image found
                    imageImageView.setImageDrawable(getResources().getDrawable(R.drawable.default_image));
                }

                // ADD REVIEW
                findViewById(R.id.buttonAddReview).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddReviewModalFragment dialogFragment = new AddReviewModalFragment();
                        dialogFragment.setReviewListener(detailsRestaurantActivity);
                        dialogFragment.show(getSupportFragmentManager(), "AddReviewModalFragment");
                    }
                });

                findViewById(R.id.app_name).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DetailsRestaurantActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    }
                });

            }
        }
    }

    /**
     *  Called by fragment on "Envoyer avis" action
     *  Create comment and add it to the recycler view
     */
    @Override
    public void onReviewSubmitted(String lastName, String firstName, String title, String comment, float note, Date date) {

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("restaurant")) {
            Restaurant restaurant = intent.getParcelableExtra("restaurant");
            if (restaurant != null) {

                FirebaseAccess firebaseAccess = FirebaseAccess.getInstance();
                DatabaseReference databaseReference = firebaseAccess.getDatabase().getReference("users");
                String newId = databaseReference.push().getKey();
                databaseReference.child(newId).setValue(new User(newId, firstName, lastName));

                databaseReference = firebaseAccess.getDatabase().getReference("comments");
                Comment newComment = new Comment(restaurant.getId(), newId, date, title, comment, (int) note * 2);
                newId = databaseReference.push().getKey();
                databaseReference.child(newId).setValue(newComment);

                // Refresh view list
                AdapterComment adapter = new AdapterComment(null, null);
                RecyclerView recyclerView = findViewById(R.id.list_comments);
                recyclerView.setAdapter(adapter);
            }
        }
    }

    private void fetchAllWriters(Restaurant restaurant) {

        FirebaseAccess firebaseAccess = FirebaseAccess.getInstance();
        DatabaseReference databaseReference = firebaseAccess.getDatabase().getReference("users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<User> userList = new ArrayList<>();
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    userList.add(user);
                }
                fetchCommentsByRestaurant(restaurant, userList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void fetchCommentsByRestaurant(Restaurant restaurant, List<User> userList) {

        FirebaseAccess firebaseAccess = FirebaseAccess.getInstance();
        DatabaseReference databaseReference = firebaseAccess.getDatabase().getReference("comments");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Comment> commentList = new ArrayList<>();
                for (DataSnapshot commentSnapshot: dataSnapshot.getChildren()) {
                    Comment comment = commentSnapshot.getValue(Comment.class);
                    commentList.add(comment);
                }

                commentList = commentList.stream().filter(comment -> comment.getRestaurantId().equals(restaurant.getId())).collect(Collectors.toList());

                AdapterComment adapter = new AdapterComment(commentList, userList);
                RecyclerView recyclerView = findViewById(R.id.list_comments);
                recyclerView.setAdapter(adapter);

                recyclerView.setLayoutManager(new LinearLayoutManager(DetailsRestaurantActivity.this));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
