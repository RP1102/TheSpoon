package com.example.thespoon;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thespoon.Entity.Comment;

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
        Log.d("test", String.valueOf(commentList.size()));
        Comment comment = commentList.get(position);
        holder.firstNameTextView.setText(comment.getWriter().getFirstName());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView firstNameTextView;
        ImageView lastNameTextView;
        TextView dateTextView;
        TextView titleTextView;
        TextView contentTextView;
        TextView rateTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            firstNameTextView = itemView.findViewById(R.id.firstNameTextView);
        }
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }
}
