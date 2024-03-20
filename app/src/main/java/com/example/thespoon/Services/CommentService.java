package com.example.thespoon.Services;



import com.example.thespoon.Entity.Comment;
import com.example.thespoon.Entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentService {

    List<Comment> commentList = new ArrayList<>();

    public void addComment(Comment comment){
        this.commentList.add(comment);
    }
/*
    public List<Comment> getCommentsByRestaurant(String restaurantName){
        return this.commentList.stream().filter(comment -> comment.getRestaurantName().equals(restaurantName)).collect(Collectors.toList());
    }
    
 */

    public List<Comment> getAll(){
        return this.commentList;
    }

    public void createRandomComments() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse("30/09/2000");

        this.commentList.add(new Comment(new User("Romain", "PONTACQ"),date, "Sorami le banger","j'ai adoré ", 10));
        this.commentList.add(new Comment(new User("Mathis", "VACHER"),new Date(), "Sorami c'est pafou","j'ai destesté ", 0));
        this.commentList.add(new Comment(new User("Romain", "PONTACQ"),date, "Sorami le banger","j'ai adoré ", 10));
        this.commentList.add(new Comment(new User("Mathis", "VACHER"),new Date(), "Sorami c'est pafou","j'ai destesté ", 0));
        this.commentList.add(new Comment(new User("Romain", "PONTACQ"),date, "Sorami le banger","j'ai adoré ", 10));
        this.commentList.add(new Comment(new User("Mathis", "VACHER"),new Date(), "Sorami c'est pafou","j'ai destesté ", 0));
        this.commentList.add(new Comment(new User("Romain", "PONTACQ"),date, "Sorami le banger","j'ai adoré ", 10));
        this.commentList.add(new Comment(new User("Mathis", "VACHER"),new Date(), "Sorami c'est pafou","j'ai destesté ", 0));
        this.commentList.add(new Comment(new User("Romain", "PONTACQ"),date, "Sorami le banger","j'ai adoré ", 10));
        this.commentList.add(new Comment(new User("Mathis", "VACHER"),new Date(), "Sorami c'est pafou","j'ai destesté ", 0));
        this.commentList.add(new Comment(new User("Romain", "PONTACQ"),date, "Sorami le banger","j'ai adoré ", 10));
        this.commentList.add(new Comment(new User("Mathis", "VACHER"),new Date(), "Sorami c'est pafou","j'ai destesté ", 0));
        this.commentList.add(new Comment(new User("Romain", "PONTACQ"),date, "Sorami le banger","j'ai adoré ", 10));
        this.commentList.add(new Comment(new User("Mathis", "VACHER"),new Date(), "Sorami c'est pafou","j'ai destesté ", 0));
        this.commentList.add(new Comment(new User("Romain", "PONTACQ"),date, "Sorami le banger","j'ai adoré ", 10));
        this.commentList.add(new Comment(new User("Mathis", "VACHER"),new Date(), "Sorami c'est pafou","j'ai destesté ", 0));
        this.commentList.add(new Comment(new User("Romain", "PONTACQ"),date, "Sorami le banger","j'ai adoré ", 10));
        this.commentList.add(new Comment(new User("Mathis", "VACHER"),new Date(), "Sorami c'est pafou","j'ai destesté ", 0));
        this.commentList.add(new Comment(new User("Romain", "PONTACQ"),date, "Sorami le banger","j'ai adoré ", 10));
        this.commentList.add(new Comment(new User("Mathis", "VACHER"),new Date(), "Sorami c'est pafou","j'ai destesté ", 0));
    }
}
