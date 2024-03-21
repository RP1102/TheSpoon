package com.example.thespoon.Entity;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Date;

public class Comment implements Parcelable {


    private Date date;
    private String title;
    private String text;
    private Integer rate;

    // CONSTRUCTOR
    public Comment(Date date, String title, String text, Integer rate) {
        this.date = date;
        this.title = title;
        this.text = text;
        this.rate = rate;
    }

    public Comment() {
    }

    // GETTERS & SETTERS

    public Date getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Integer getRate() {
        return rate;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    // IMPLEMENT PARCELABLE
    protected Comment(Parcel in) {
        date = new Date(in.readLong());
        title = in.readString();
        text = in.readString();
        rate = in.readInt();
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(date.getTime());
        dest.writeString(title);
        dest.writeString(text);
        dest.writeInt(rate);
    }
}
