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

import com.example.thespoon.Entity.Comment;
import com.example.thespoon.Entity.Restaurant;
import com.example.thespoon.Entity.User;
import com.example.thespoon.Fragment.AddReviewModalFragment;

import java.util.Date;

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
                // Initialize Recycler view for comments
                RecyclerView recyclerView = findViewById(R.id.list_comments);
                AdapterComment adapter = new AdapterComment(restaurant.getCommentList());
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
                imageImageView.setImageDrawable(getDrawable(R.drawable.default_image));
                addressTextView.setText(restaurant.getAddress());
                typeTextView.setText(restaurant.getType().getLabel());
                averagePriceTextView.setText(restaurant.getAveragePrice().toString());
                rateTextView.setText(restaurant.getRate().getCalculatedRate().toString());
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
                // Add new Comment
                Comment newComment = new Comment(new User(firstName, lastName), date, title, comment, (int) note * 2);
                restaurant.getCommentList().add(0,newComment);
                // Refresh view list
                AdapterComment adapter = new AdapterComment(restaurant.getCommentList());
                RecyclerView recyclerView = findViewById(R.id.list_comments);
                recyclerView.setAdapter(adapter);
            }
        }
    }
}
