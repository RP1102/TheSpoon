package com.example.thespoon.DatabaseAccess;


import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class FirebaseAccess {

    private static FirebaseAccess instance;
    private final FirebaseDatabase database; // pour les données des resto
    private final FirebaseStorage storage; //pour les photos

    private FirebaseAccess() {
        database = FirebaseDatabase.getInstance("https://thespoon-8adcf-default-rtdb.europe-west1.firebasedatabase.app/");
        storage = FirebaseStorage.getInstance("gs://thespoon-8adcf.appspot.com");
    }

    public static synchronized FirebaseAccess getInstance() {
        if (instance == null) {
            instance = new FirebaseAccess();
        }
        return instance;
    }

}
