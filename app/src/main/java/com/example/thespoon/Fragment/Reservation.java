package com.example.thespoon.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.thespoon.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Reservation #newInstance} factory method to
 * create an instance of this fragment.
 */
public class Reservation extends Fragment {

    Calendar calendar;
    CalendarView calendarView;

    int nbPerson = 0;
    private Button lastButtonClicked;

    public Reservation() {
        // Required empty public constructor
    }

    public void changeBtnColor(View v){
        if(lastButtonClicked != null){
            lastButtonClicked.setBackgroundColor(Color.parseColor("#DDDDDD"));
        }
        lastButtonClicked = (Button) v;
        lastButtonClicked.setBackgroundColor(Color.parseColor("#CCCCCC"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reservation, container, false);

        calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        calendar = Calendar.getInstance();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
            }
        });


        Button buttonOne = view.findViewById(R.id.button_one);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbPerson = 1;
                changeBtnColor(v);
            }
        });

        Button buttonTwo = view.findViewById(R.id.button_two);
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbPerson = 2;
                changeBtnColor(v);
            }
        });

        Button buttonThree = view.findViewById(R.id.button_three);
        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbPerson = 3;
                changeBtnColor(v);}
        });

        Button buttonFour = view.findViewById(R.id.button_four);
        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbPerson = 4;
                changeBtnColor(v);
            }
        });

        Button buttonReserve = view.findViewById(R.id.button_reserve);
        buttonReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nbPerson == 0)
                    Toast.makeText(getActivity(), "Veuillez saisir un nombre de personne", Toast.LENGTH_SHORT).show();
                else if (calendar.getTime() != null && calendar.getTime().before(Calendar.getInstance().getTime()))
                    Toast.makeText(getActivity(), "Veuillez saisir une date valide", Toast.LENGTH_SHORT).show();
                else if(nbPerson != 0 && calendar.getTime() != null) {
                    Toast.makeText(getActivity(), "Votre réservation à bien été enregistré pour " + nbPerson + " personnes.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
}