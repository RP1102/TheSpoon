package com.example.thespoon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thespoon.Entity.Comment;

import java.util.Date;
import java.util.List;

public class AdapterComment extends RecyclerView.Adapter<AdapterComment.ViewHolder> {

    List<Comment> commentList;

    public AdapterComment(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.titleTextView.setText(comment.getTitle());
        holder.rateTextView.setText(comment.getRate().toString());
        holder.contentTextView.setText(comment.getText());
//        holder.firstNameTextView.setText(comment.getWriter().getFirstName());
//        holder.lastNameTextView.setText(comment.getWriter().getLastName());
        holder.dateTextView.setText(formatDateDifference(comment.getDate()));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView firstNameTextView;
        TextView lastNameTextView;
        TextView dateTextView;
        TextView titleTextView;
        TextView contentTextView;
        TextView rateTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            rateTextView = itemView.findViewById(R.id.rateTextView);
            firstNameTextView = itemView.findViewById(R.id.firstNameTextView);
            lastNameTextView = itemView.findViewById(R.id.lastNameTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }


    public static String formatDateDifference(Date date) {
        long diffInMillis = new Date().getTime() - date.getTime();
        long diffInSeconds = diffInMillis / 1000;
        long diffInMinutes = diffInSeconds / 60;
        long diffInHours = diffInMinutes / 60;
        long diffInDays = diffInHours / 24;

        if (diffInDays == 0) {
            return "aujourd'hui";
        } else if (diffInDays == 1) {
            return "hier";
        } else if (diffInDays < 7) {
            return "il y a " + diffInDays + " jours";
        } else if (diffInDays < 30) {
            long diffInWeeks = diffInDays / 7;
            return "il y a " + diffInWeeks + (diffInWeeks > 1 ? " semaines" : " semaine");
        } else if (diffInDays < 365) {
            long diffInMonths = diffInDays / 30;
            return "il y a " + diffInMonths + " mois";
        } else {
            long diffInYears = diffInDays / 365;
            return "il y a " + diffInYears + (diffInYears > 1 ? " ans" : " an");
        }
    }
}
