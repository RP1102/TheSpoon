package com.example.thespoon;

import java.util.Date;

public interface FragmentListener {
    /**
     * Send data to parent activity
     * @param lastName lastName
     * @param firstName firstName
     * @param title title
     * @param comment comment
     * @param rate rate
     * @param rate rate
     */
    void onReviewSubmitted(String lastName, String firstName, String title, String comment, float rate, Date date);
}
