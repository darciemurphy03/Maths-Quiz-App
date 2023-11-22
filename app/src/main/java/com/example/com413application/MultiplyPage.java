package com.example.com413application;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.ktx.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MultiplyPage extends Fragment {

    TextView scoreView;
    TextView questionView;
    Button btnChoice1, btnChoice2, btnChoice3, btnChoice4;
    int score;
    String answer;
    Firebase questionRef, choice1Ref, choice2Ref, choice3Ref, choice4Ref, answerRef;
    public MultiplyPage() {
        //
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiply, container, false);

        scoreView = (TextView)view.findViewById(R.id.score);
        questionView = (TextView)view.findViewById(R.id.question);

        btnChoice1 = (Button)view.findViewById(R.id.choice1);
        btnChoice2 = (Button)view.findViewById(R.id.choice2);
        btnChoice3 = (Button)view.findViewById(R.id.choice3);
        btnChoice4 = (Button)view.findViewById(R.id.choice4);


        return view;
    }

    public void updateQuestion() {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("questions");

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String questionText = snapshot.child("text").getValue(String.class);
                    List<String> choices = (List<String>) snapshot.child("choices").getValue();

                    questionView.setText(questionText);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error if needed
            }
        });
    }
}
