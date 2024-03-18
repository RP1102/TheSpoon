package com.example.thespoon.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class Comment implements Parcelable {


    private User writer;

    private Date date;

    private String title;
    private String text;

    private Integer rate;

    // CONSTRUCTOR
    public Comment(User writer, Date date, String title, String text, Integer rate) {
        this.writer = writer;
        this.date = date;
        this.title = title;
        this.text = text;
        this.rate = rate;
    }

    // GETTERS & SETTERS
    public User getWriter() {
        return writer;
    }

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

    public void setWriter(User writer) {
        this.writer = writer;
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
        writer = in.readParcelable(User.class.getClassLoader());
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
        dest.writeParcelable(writer, flags);
        dest.writeLong(date.getTime());
        dest.writeString(title);
        dest.writeString(text);
        dest.writeInt(rate);
    }
}
