package com.example.com413application;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MultiplyPage extends Fragment {

    TextView scoreView;
    TextView questionView;
    Button btnChoice1, btnChoice2, btnChoice3, btnChoice4;
    int score;
    String answer;
    DatabaseReference questionRef, choice1Ref, choice2Ref, choice3Ref, choice4Ref, answerRef;
    public MultiplyPage() {
        //
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiply, container, false);
        FirebaseApp.initializeApp(requireContext());

        scoreView = (TextView)view.findViewById(R.id.score);
        questionView = (TextView)view.findViewById(R.id.question);

        btnChoice1 = (Button)view.findViewById(R.id.choice1);
        btnChoice2 = (Button)view.findViewById(R.id.choice2);
        btnChoice3 = (Button)view.findViewById(R.id.choice3);
        btnChoice4 = (Button)view.findViewById(R.id.choice4);

        updateQuestion();

        return view;
    }

    public void updateQuestion() {
        questionRef = FirebaseDatabase.getInstance().getReference().child("0").child("question");
        questionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String question = dataSnapshot.getValue(String.class);
                questionView.setText(question);
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        choice1Ref = FirebaseDatabase.getInstance().getReference().child("0").child("choice1");
        choice1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
                btnChoice1.setText(choice);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
