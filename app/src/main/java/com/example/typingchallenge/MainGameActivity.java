package com.example.typingchallenge;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class MainGameActivity extends AppCompatActivity {

    TextView wroten_text;
    TextView word;
    int speed = 25000;
    int score = 0;
    StringBuilder text = new StringBuilder();
    String[] words_level_1 = {"and", "fix", "own", "are", "fly", "odd", "ape", "fry", "our", "ace", "for", "pet", "act", "got", "pat", "ask", "get", "peg", "arm", "god", "paw", "age", "gel", "pup", "ago", "gas", "pit", "air", "hat", "put", "ate", "hit", "pot", "all", "has", "pop", "but", "had", "pin", "bye", "how", "rat", "bad", "her", "rag", "big", "his", "bed", "hen", "row", "bat", "ink", "rug", "boy", "ice", "run", "bus", "rap", "bag", "jab", "ram", "box", "jug", "sow", "bit", "jet", "see", "bee", "jam", "saw", "buy", "jar", "set", "bun", "job", "sit", "cub", "jog", "sir", "cat", "kit", "sat", "car", "key", "sob", "cut", "lot", "tap", "cow", "lit", "tip", "cry", "let", "top", "cab", "lay", "tug", "can", "mat", "tow", "dad", "man", "toe", "dab", "mad", "tan", "dam", "mug", "ten", "did", "mix", "two", "dug", "map", "use", "den", "van", "dot", "mud", "vet", "dip", "mom", "was", "day", "may", "wet", "ear", "met", "win", "eye", "net", "won", "eat", "new", "end", "nap", "war", "elf", "now", "why", "egg", "nod", "who", "far", "net", "way", "fat", "not", "wow", "few", "nut", "you", "fan", "oar", "yes", "fun", "one", "fit", "out", "yet", "fin", "owl", "zip", "fox", "old", "zap"};
    Animation fallingAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        text.append(" ");

        wroten_text = findViewById(R.id.wroten_text);
        word = findViewById(R.id.word);

        fallingAnimation = createFallingAnimation();
        startFallingAnimation();

        getRandomWord();
        startFallingAnimation();

        //Row first
        TextView q_TextView = findViewById(R.id.q_button);
        TextView w_TextView = findViewById(R.id.w_button);
        TextView e_TextView = findViewById(R.id.e_button);
        TextView r_TextView = findViewById(R.id.r_button);
        TextView t_TextView = findViewById(R.id.t_button);
        TextView y_TextView = findViewById(R.id.y_button);
        TextView u_TextView = findViewById(R.id.u_button);
        TextView i_TextView = findViewById(R.id.i_button);
        TextView o_TextView = findViewById(R.id.o_button);
        TextView p_TextView = findViewById(R.id.p_button);

        //Row second
        TextView a_TextView = findViewById(R.id.a_button);
        TextView s_TextView = findViewById(R.id.s_button);
        TextView d_TextView = findViewById(R.id.d_button);
        TextView f_TextView = findViewById(R.id.f_button);
        TextView g_TextView = findViewById(R.id.g_button);
        TextView h_TextView = findViewById(R.id.h_button);
        TextView j_TextView = findViewById(R.id.j_button);
        TextView k_TextView = findViewById(R.id.k_button);
        TextView l_TextView = findViewById(R.id.l_button);

        //Row third
        TextView z_TextView = findViewById(R.id.z_button);
        TextView x_TextView = findViewById(R.id.x_button);
        TextView c_TextView = findViewById(R.id.c_button);
        TextView v_TextView = findViewById(R.id.v_button);
        TextView b_TextView = findViewById(R.id.b_button);
        TextView n_TextView = findViewById(R.id.n_button);
        TextView m_TextView = findViewById(R.id.m_button);

        //Special buttons
        TextView backspace_TextView = findViewById(R.id.backspace_button);
        TextView space_TextView = findViewById(R.id.space_button);

        View.OnClickListener textViewClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof TextView) {
                    TextView clickedTextView = (TextView) v;
                    String buttonText = clickedTextView.getText().toString().toLowerCase();
                    text.append(buttonText);
                    wroten_text.setText(text.toString());
                    if (checkLastStrings()) {
                        checkLastStrings();
                        getRandomWord();
                        score += word.length() * 10;
                        text.setLength(0);
                        wroten_text.setText(" ");
                        startFallingAnimation();
                    }
                }
            }
        };

        backspace_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text.length() > 0) {
                    text.deleteCharAt(text.length() - 1);
                    wroten_text.setText(text.toString());
                }
            }
        });

        space_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.append(" ");
                wroten_text.setText(text.toString());
            }
        });

        //Row first
        q_TextView.setOnClickListener(textViewClickListener);
        w_TextView.setOnClickListener(textViewClickListener);
        e_TextView.setOnClickListener(textViewClickListener);
        r_TextView.setOnClickListener(textViewClickListener);
        t_TextView.setOnClickListener(textViewClickListener);
        y_TextView.setOnClickListener(textViewClickListener);
        u_TextView.setOnClickListener(textViewClickListener);
        i_TextView.setOnClickListener(textViewClickListener);
        o_TextView.setOnClickListener(textViewClickListener);
        p_TextView.setOnClickListener(textViewClickListener);

        //Row second
        a_TextView.setOnClickListener(textViewClickListener);
        s_TextView.setOnClickListener(textViewClickListener);
        d_TextView.setOnClickListener(textViewClickListener);
        f_TextView.setOnClickListener(textViewClickListener);
        g_TextView.setOnClickListener(textViewClickListener);
        h_TextView.setOnClickListener(textViewClickListener);
        j_TextView.setOnClickListener(textViewClickListener);
        k_TextView.setOnClickListener(textViewClickListener);
        l_TextView.setOnClickListener(textViewClickListener);

        //Row third
        z_TextView.setOnClickListener(textViewClickListener);
        x_TextView.setOnClickListener(textViewClickListener);
        c_TextView.setOnClickListener(textViewClickListener);
        v_TextView.setOnClickListener(textViewClickListener);
        b_TextView.setOnClickListener(textViewClickListener);
        n_TextView.setOnClickListener(textViewClickListener);
        m_TextView.setOnClickListener(textViewClickListener);
    }

    protected String getRandomWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(words_level_1.length);
        word.setText(words_level_1[randomIndex]);
        startFallingAnimation();
        return words_level_1[randomIndex];
    }

    protected String getCurrentWord() {
        return word.getText().toString();
    }

    protected void startFallingAnimation() {
        word.clearAnimation();
        word.startAnimation(fallingAnimation);
    }

    private boolean checkLastStrings() {
        String typedText = text.toString();
        int wordLength = word.length();

        if (typedText.length() >= word.length()) {
            String text1 = typedText.substring(typedText.length() - wordLength);

            if (text1.equals(word.getText().toString())) {
                getRandomWord();
                return true;
            }
        }
        return false;
    }


    //Animation
    private Animation createFallingAnimation() {
        int screenHeight = getResources().getDisplayMetrics().heightPixels;

        TranslateAnimation animation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, (float) screenHeight / 100 * 70);

        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(speed);
        animation.setRepeatCount(Animation.INFINITE);

        return animation;
    }


}
