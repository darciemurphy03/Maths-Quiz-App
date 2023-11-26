package com.example.com413application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class scoreDisplayPage extends AppCompatActivity {

    TextView scoreDisplay;
    Button btn_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_display_page);

        scoreDisplay = (TextView)findViewById(R.id.scoreDisplay);
        btn_return = (Button)findViewById(R.id.btn_return);

        int score = getIntent().getIntExtra("SCORE_KEY", 0);
        scoreDisplay.setText("Score: " + score);

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(scoreDisplayPage.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}