package com.example.typingchallenge;// MainActivity.java
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.typingchallenge.R;

public class MainActivity extends AppCompatActivity {

    private TextView fallingWord;
    private EditText userInput;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fallingWord = findViewById(R.id.fallingWord);
        userInput = findViewById(R.id.userInput);

        Animation fallAnimation = AnimationUtils.loadAnimation(this, R.anim.fall_animation);
        fallingWord.startAnimation(fallAnimation);

        userInput.setOnEditorActionListener((v, actionId, event) -> {
            String typedWord = userInput.getText().toString().trim();
            String currentFallingWord = fallingWord.getText().toString().trim();

            if (typedWord.equalsIgnoreCase(currentFallingWord)) {
                updateFallingWord();
                userInput.setText("");
            }

            return false;
        });
    }

    private void updateFallingWord() {
        String[] words = {"Apple", "Banana", "Orange", "Grapes", "Mango"};
        int randomIndex = (int) (Math.random() * words.length);
        String newFallingWord = words[randomIndex];
        fallingWord.setText(newFallingWord);
    }
}
