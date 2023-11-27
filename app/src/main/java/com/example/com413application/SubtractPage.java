package com.example.com413application;


import android.content.Intent;
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

public class SubtractPage extends Fragment {

    TextView scoreView;
    TextView questionView;
    TextView qNumberDisplay;
    Button btnChoice1, btnChoice2, btnChoice3, btnChoice4;
    public int score;
    int questionNumber = 30;
    int displayQuestionNumber = 0;
    String answer;
    DatabaseReference questionRef, choice1Ref, choice2Ref, choice3Ref, choice4Ref, answerRef;
    public SubtractPage() {
        //
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subtract, container, false);
        FirebaseApp.initializeApp(requireContext());

        scoreView = (TextView)view.findViewById(R.id.score);
        questionView = (TextView)view.findViewById(R.id.question);
        qNumberDisplay = (TextView)view.findViewById(R.id.qNumberText);

        btnChoice1 = (Button)view.findViewById(R.id.choice1);
        btnChoice2 = (Button)view.findViewById(R.id.choice2);
        btnChoice3 = (Button)view.findViewById(R.id.choice3);
        btnChoice4 = (Button)view.findViewById(R.id.choice4);

        updateQuestion();
        updateChoice();

        // Button 1
        btnChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnChoice1.getText().equals(answer)) {
                    score = score + 1;
                    updateScore(score);
                    updateQuestion();
                    updateChoice();
                } else {
                    updateQuestion();
                    updateChoice();
                }
            }
        });

        // Button 2
        btnChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnChoice2.getText().equals(answer)) {
                    score = score + 1;
                    updateScore(score);
                    updateQuestion();
                    updateChoice();
                } else {
                    updateQuestion();
                    updateChoice();
                }
            }
        });

        // Button 3
        btnChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnChoice3.getText().equals(answer)) {
                    score = score + 1;
                    updateScore(score);
                    updateQuestion();
                    updateChoice();
                } else {
                    updateQuestion();
                    updateChoice();
                }
            }
        });

        // Button 4
        btnChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnChoice4.getText().equals(answer)) {
                    score = score + 1;
                    updateScore(score);
                    updateQuestion();
                    updateChoice();
                } else {
                    updateQuestion();
                    updateChoice();
                }
            }
        });

        return view;
    }

    private void updateScore(int score) {
        scoreView.setText("" + score);
    }

    public void updateQuestion() {

        String qNumber = String.valueOf(questionNumber);
        String displayQuestion = String.valueOf(displayQuestionNumber + 1);
        displayQuestionNumber++;
        qNumberDisplay.setText("Question " + displayQuestionNumber);
        questionRef = FirebaseDatabase.getInstance().getReference().child(qNumber).child("question");

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
    }

    public void updateChoice() {

        choice1Ref = FirebaseDatabase.getInstance().getReference().child(String.valueOf(questionNumber)).child("choice1");
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


        choice2Ref = FirebaseDatabase.getInstance().getReference().child(String.valueOf(questionNumber)).child("choice2");
        choice2Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
                btnChoice2.setText(choice);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        choice3Ref = FirebaseDatabase.getInstance().getReference().child(String.valueOf(questionNumber)).child("choice3");
        choice3Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
                btnChoice3.setText(choice);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                String a = "";
            }
        });
        choice4Ref = FirebaseDatabase.getInstance().getReference().child(String.valueOf(questionNumber)).child("choice4");
        choice4Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
                btnChoice4.setText(choice);
                questionNumber++;
                if (questionNumber == 41) {

                    Intent i = new Intent(getActivity(), scoreDisplayPage.class);
                    i.putExtra("SCORE_KEY", score);
                    startActivity(i);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        answerRef = FirebaseDatabase.getInstance().getReference().child(String.valueOf(questionNumber)).child("answer");
        answerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                answer = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}


