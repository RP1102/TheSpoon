package com.example.thespoon.DatabaseManager;

import com.google.firebase.database.FirebaseDatabase;

public class FirebaseAccess {

    private static FirebaseAccess instance;
    private final FirebaseDatabase database; // pour les données des resto
    //private final FirebaseStorage storage; //pour les photos

    private FirebaseAccess() {
        database = FirebaseDatabase.getInstance("https://thespoon-24a2e-default-rtdb.europe-west1.firebasedatabase.app");
        //storage = FirebaseStorage.getInstance("gs://thespoon-8adcf.appspot.com");
    }

    public static synchronized FirebaseAccess getInstance() {
        if (instance == null) {
            instance = new FirebaseAccess();
        }
        return instance;
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    /*
    public FirebaseStorage getStorage() {
        return storage;
    }
     */
}
