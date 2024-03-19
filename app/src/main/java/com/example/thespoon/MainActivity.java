package com.example.thespoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thespoon.Entity.Comment;
import com.example.thespoon.Entity.Rate;
import com.example.thespoon.Entity.User;
import com.example.thespoon.Enum.FoodTypeEnum;
import com.example.thespoon.Entity.Restaurant;

import android.content.Intent;
import android.os.Bundle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.list_restaurants);

        List<Restaurant> restaurants;
        try {
            restaurants = getRestaurants();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        AdapterRestaurant adapter = new AdapterRestaurant(restaurants);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnItemClickListener(new AdapterRestaurant.OnItemClickListener() {
            @Override
            public void onItemClick(Restaurant restaurant) {

                Intent intent = new Intent(MainActivity.this, DetailsRestaurantActivity.class);
                intent.putExtra("restaurant",restaurant);
                startActivity(intent);

            }
        });


    }

    private List<Restaurant> getRestaurants() throws ParseException {

        List<Restaurant> restaurantList = new ArrayList<>();
        List<Integer> ratesList = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            int randomNumber = rand.nextInt(10) + 1;
            ratesList.add(randomNumber);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = "30/09/2000";
        Date date = sdf.parse(dateString);

        List<Comment> commentList = new ArrayList<>();
        commentList.add(new Comment(new User("Romain", "PONTACQ"),date, "Sorami le banger","j'ai adoré ", 10));
        commentList.add(new Comment(new User("Mathis", "VACHER"),new Date(), "Sorami c'est pafou","j'ai destesté ", 0));
        commentList.add(new Comment(new User("Romain", "PONTACQ"),date, "Sorami le banger","j'ai adoré ", 10));
        commentList.add(new Comment(new User("Mathis", "VACHER"),new Date(), "Sorami c'est pafou","j'ai destesté ", 0));
        commentList.add(new Comment(new User("Romain", "PONTACQ"),date, "Sorami le banger","j'ai adoré ", 10));
        commentList.add(new Comment(new User("Mathis", "VACHER"),new Date(), "Sorami c'est pafou","j'ai destesté ", 0));
        commentList.add(new Comment(new User("Romain", "PONTACQ"),date, "Sorami le banger","j'ai adoré ", 10));
        commentList.add(new Comment(new User("Mathis", "VACHER"),new Date(), "Sorami c'est pafou","j'ai destesté ", 0));
        commentList.add(new Comment(new User("Romain", "PONTACQ"),date, "Sorami le banger","j'ai adoré ", 10));
        commentList.add(new Comment(new User("Mathis", "VACHER"),new Date(), "Sorami c'est pafou","j'ai destesté ", 0));


        for (int i = 0; i < 5; i++) {
            Restaurant r = new Restaurant("nom_" + i, "description_" + i,"address_" + i,"restaurant", FoodTypeEnum.AFRICA, 10, new Rate(ratesList), commentList);
            restaurantList.add(r);
        }
        return restaurantList;
    }
}