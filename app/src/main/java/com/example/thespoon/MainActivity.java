package com.example.thespoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thespoon.Entity.Rate;
import com.example.thespoon.Enum.FoodTypeEnum;
import com.example.thespoon.Entity.Restaurant;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.list_restaurants);

        List<Restaurant> restaurants = getRestaurants();

        AdapterRestaurant adapter = new AdapterRestaurant(restaurants);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    private List<Restaurant> getRestaurants() {

        List<Restaurant> restaurantList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Restaurant r = new Restaurant("nom_" + i, "description_" + i,"address_" + i,"restaurant", FoodTypeEnum.AFRICA, 10, new Rate());
            restaurantList.add(r);
        }
        return restaurantList;
    }
}