
package com.example.typingchallenge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity; // Add this import statement

public class MainGameOver extends AppCompatActivity {

    MainGameActivity mainGameActivity;

    int lastScore;
    TextView score_gameOver;
    ImageButton goBackButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_over);

        Intent intent = getIntent();
        if (intent != null) {
            lastScore = intent.getIntExtra("SCORE", 0);
        }

        score_gameOver = findViewById(R.id.score_gameOver);
        //goBackButton = findViewById(R.id.goBackButton);
        score_gameOver.setText("Your score is : " + lastScore);

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainGameOver.this, MainGameActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
