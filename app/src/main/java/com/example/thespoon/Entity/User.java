package com.example.thespoon.Entity;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String firstName;

    private String lastName;

    private String id;



    // CONSTRUCTOR
    public User(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(){
    }


    // GETTERS & SETTERS
    public String getFirstName() {
        return firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // IMPLEMENT PARCELABLE
    protected User(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        id = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(id);
    }
}
