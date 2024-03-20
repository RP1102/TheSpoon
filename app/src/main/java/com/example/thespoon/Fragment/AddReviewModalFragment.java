package com.example.thespoon.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.thespoon.FragmentListener;
import com.example.thespoon.R;

import java.util.Date;

public class AddReviewModalFragment extends DialogFragment {

    private EditText editTextLastNale, editTextFirstName, editTextTitle, editTextComment;
    private RatingBar ratingBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_review_modal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextLastNale = view.findViewById(R.id.editTextLastName);
        editTextFirstName = view.findViewById(R.id.editTextFirstName);
        editTextTitle = view.findViewById(R.id.editTextTitle);
        editTextComment = view.findViewById(R.id.editTextComment);
        ratingBar = view.findViewById(R.id.ratingBar);

        // Define button "Envoyer Avis" action
        Button buttonSendReview = view.findViewById(R.id.buttonSendReview);
        buttonSendReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lastName = editTextLastNale.getText().toString();
                String firstName = editTextFirstName.getText().toString();
                String title = editTextTitle.getText().toString();
                String comment = editTextComment.getText().toString();
                float note = ratingBar.getRating();

                // Check minimum values to create a review
                if (note > 0 && (!lastName.isEmpty() || !firstName.isEmpty()) && !title.isEmpty()) {
                    if (fragmentListener != null) {
                        fragmentListener.onReviewSubmitted(lastName, firstName, title, comment, note, new Date());
                        dismiss();
                    }
                } else {
                    // If condition false then display alert msg
                    Toast.makeText(getContext(), "Veuillez saisir au moins un titre, une note et un nom ou prénom pour ajouter un avis.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private FragmentListener fragmentListener;

    public void setReviewListener(FragmentListener listener) {
        this.fragmentListener = listener;
    }
    

    @Override
    public void onResume() {
        super.onResume();
        // Define sizing of the popup
        if (getDialog() != null && getDialog().getWindow() != null) {
            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.8); 
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            getDialog().getWindow().setAttributes(params);
        }
    }
}