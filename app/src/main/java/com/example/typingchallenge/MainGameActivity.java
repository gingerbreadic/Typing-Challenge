package com.example.typingchallenge;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


import java.util.Random;


public class MainGameActivity extends AppCompatActivity {
    private Paint paint = new Paint();
    TextView wroten_text;
    TextView score_textView;
    TextView health_textView;
    TextView word;
    int screenHeight;

    int speed;
    int score = 0;
    int health = 10;

    StringBuilder text = new StringBuilder();
    String[] words_level_1 = {"and", "fix", "own", "are", "fly", "odd", "ape", "fry", "our", "ace", "for", "pet", "act", "got", "pat", "ask", "get", "peg", "arm", "god", "paw", "age", "gel", "pup", "ago", "gas", "pit", "air", "hat", "put", "ate", "hit", "pot", "all", "has", "pop", "but", "had", "pin", "bye", "how", "rat", "bad", "her", "rag", "big", "his", "bed", "hen", "row", "bat", "ink", "rug", "boy", "ice", "run", "bus", "rap", "bag", "jab", "ram", "box", "jug", "sow", "bit", "jet", "see", "bee", "jam", "saw", "buy", "jar", "set", "bun", "job", "sit", "cub", "jog", "sir", "cat", "kit", "sat", "car", "key", "sob", "cut", "lot", "tap", "cow", "lit", "tip", "cry", "let", "top", "cab", "lay", "tug", "can", "mat", "tow", "dad", "man", "toe", "dab", "mad", "tan", "dam", "mug", "ten", "did", "mix", "two", "dug", "map", "use", "den", "van", "dot", "mud", "vet", "dip", "mom", "was", "day", "may", "wet", "ear", "met", "win", "eye", "net", "won", "eat", "new", "end", "nap", "war", "elf", "now", "why", "egg", "nod", "who", "far", "net", "way", "fat", "not", "wow", "few", "nut", "you", "fan", "oar", "yes", "fun", "one", "fit", "out", "yet", "fin", "owl", "zip", "fox", "old", "zap"};
    String[] words_level_2 = {"fire", "dark", "wind", "rose", "jump", "frog", "moon", "kiss", "luck", "blue", "star", "song", "bird", "love", "mind", "read", "play", "tree", "hope", "rain", "home", "time", "soft", "wave", "warm", "gold", "snow", "girl", "cool", "idea", "path", "echo", "walk", "fade", "swim", "true", "beam", "leaf", "rich", "wise", "word", "rock", "open", "peak", "fall", "wait", "mood", "gray", "calm", "wild", "dawn", "deep", "hush", "pure", "rise", "fair", "wish", "bold", "maze", "kind", "lamp", "twig", "zone", "gaze", "jazz", "ripe", "quit", "void", "fume", "fuel", "vase", "edge", "bend", "tail", "hope", "raft", "echo", "tide", "code", "mint", "lily", "foam", "dust", "fist", "fern", "ruby", "glow", "gear", "coin", "hike", "neon", "loop", "dear", "bark", "dart", "iris", "moon", "palm", "vibe", "maze", "core", "urge", "ruby", "lake", "dove", "muse", "knot", "axis", "grip", "beam", "nova", "axis", "opal", "balm", "veil", "trap", "pave", "knee", "page", "wise", "tide", "fern", "cove", "jest", "bent", "glee", "opal", "jolt", "muse", "plum"};
    String[] words_level_3 = {"grape", "charm", "flame", "ocean", "brush", "arrow", "dream", "crisp", "spark", "stone", "vivid", "blend", "lunar", "wrist", "blaze", "honey", "jumbo", "globe", "amber", "sweep", "swirl", "chill", "clove", "swift", "frost", "spire", "beard", "drift", "blitz", "spike", "smoke", "crane", "bloom", "sheep", "gazer", "curve", "fleet", "rover", "bongo", "laser", "flute", "sleet", "globe", "azure", "vocal", "savor", "glint", "plush", "shine", "pouch", "gleam", "quilt", "vista", "plume", "moose", "hound", "joust", "tramp", "quark", "pupil", "quill", "cider", "swoop", "flint", "roost", "chord", "smirk", "glare", "pouch", "grind", "whale", "plush", "savor", "quilt", "flock", "blitz", "mirth", "snare", "felon", "joust", "jumbo", "trove", "fable", "brisk", "crave", "pique", "lucid", "sheen", "frisk", "crisp", "prong", "sling", "quilt", "forge", "swoon", "creep", "plume", "vogue", "cleft", "rider", "blend", "glove", "quake", "mirth", "blush", "gloom", "pluck", "truce", "craze", "blink", "swank", "swirl", "blare", "sling", "flare", "briar", "swoop", "laser", "swift", "swoon", "prong", "lucid", "savor", "plume", "frost"};
    String[] words_level_4 = {"glisten", "whistle", "kettle", "breeze", "shadow", "pickle", "jigsaw", "mellow", "quiver", "flinch", "sprout", "vortex", "tinsel", "quaint", "sizzle", "stroll", "jungle", "placid", "dapper", "quartz", "fizzle", "fluent", "spiral", "dazzle", "splint", "crunch", "vividly", "beacon", "gobble", "juggle", "snappy", "marvel", "glisten", "velvet", "wisdom", "cookie", "twirls", "bronze", "jovial", "snazzy", "grinch", "gargle", "twisty", "whimsy", "quasar", "flinch", "cozy", "gusty", "snugly", "floral", "quaint", "zippy", "civic", "pixel", "jolly", "groovy", "swoosh", "razor", "bongo", "whiff", "chirpy", "sting", "placid", "crunch", "slushy", "quest", "jumpy", "snazzy", "golly", "lunar", "foggy", "blitz", "swoon", "gauzy", "brisk", "mirth", "swoop", "whale", "trend", "funky", "swank", "blush", "blink", "gloomy", "fable", "plume", "glaze", "grape", "taste", "brisk", "swoon", "prong", "swirl", "joust", "swoop", "spark", "sweep", "laser", "chill", "sheep", "crisp", "blend", "savor", "frost", "curve", "drift", "shine", "rover", "plush", "azure", "quilt", "pouch", "gleam", "jumbo", "fleet", "globe", "swift", "vivid", "stone", "spark", "crisp", "wrist", "blitz", "spike", "beard", "smoke", "crane", "bloom", "gazer"};
    String[] words_level_5 = {"whisper", "captain", "analyze", "bicycle", "freedom", "mystery", "concert", "fantasy", "victory", "diamond", "journey", "balance", "champion", "laughter", "medicine", "symphony", "critical", "fragrant", "keyboard", "strength", "boulevard", "innovate", "whenever", "sculptor", "elephant", "tomorrow", "cinnamon", "marathon", "umbrella", "quadrant", "parallel", "happiness", "infinity", "platform", "delicate", "obsidian", "sparkle", "creation", "charming", "integral", "beautiful", "solution", "adventure", "positive", "tomorrow", "radiance", "bluebird", "cascade", "longevity", "grateful", "serenity", "sunflower", "attention", "provocative", "reflection", "silhouette", "celestial", "effervescent", "inspiring", "extravaganza", "whimsical", "resilient", "creativity", "illuminate", "labyrinth", "blossom", "imagination", "juxtapose", "melodious", "reverence", "tranquil", "abundance", "whispering", "splendid", "breathtaking", "fascinate", "serendipity", "enchanting", "effulgent", "luminary", "bewitching", "inspiration", "extraordinary", "mesmerize", "quintessence", "melancholy", "charismatic", "magnificent", "reflection", "serenity", "illuminate", "graceful", "majestic", "nostalgia", "mystique", "profound", "phenomenal", "ambrosial", "rhapsody", "transcend", "luminous", "captivate", "effulgence", "sovereign", "whisper", "vibrant", "infinity", "lullaby", "oblivion", "zephyr", "breathtaking", "cynosure", "efflorescence", "mellifluous", "oblivion", "quicksilver", "magnolia", "efflorescent", "harmonious", "effulgent", "seraphic", "epiphany", "luminescent", "ethereal", "enrapture", "melancholy", "resplendent", "evanescent", "whisper"};

    Animation fallingAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        text.append(" ");

        speed = 10000;
        screenHeight = getResources().getDisplayMetrics().heightPixels;

        wroten_text = findViewById(R.id.wroten_text);
        word = findViewById(R.id.word);
        score_textView = findViewById(R.id.score_textView);
        health_textView = findViewById(R.id.health_textView);

        fallingAnimation = createFallingAnimation(true);
        startFallingAnimation();

        getRandomWord();
        startFallingAnimation();

        int keyHeight = (int) (screenHeight * 0.07);

        LinearLayout firstRow = findViewById(R.id.first_row);
        setKeyHeightForLinearLayout(firstRow, keyHeight);

        LinearLayout secondRow = findViewById(R.id.second_row);
        setKeyHeightForLinearLayout(secondRow, keyHeight);

        LinearLayout thirdRow = findViewById(R.id.third_row);
        setKeyHeightForLinearLayout(thirdRow, keyHeight);

        LinearLayout forthRow = findViewById(R.id.forth_row);
        setKeyHeightForLinearLayout(forthRow, keyHeight);

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
                        score_textView.setText("Score : " + score);
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
        int randomIndex;
        String[] array_level;

        if (score <= 1000) {
            array_level = words_level_1;
            speed -= 5;
        } else if (score > 1000 && score <= 2500) {
            array_level = words_level_2;
            speed -= 10;
        } else if (score >= 2500 && score <= 5000) {
            array_level = words_level_3;
            speed -= 15;
        } else if (score >= 5000 && score <= 6500) {
            array_level = words_level_4;
            speed -= 20;
        } else {
            array_level = words_level_5;
            speed -= 25;
        }

        randomIndex = random.nextInt(array_level.length);
        word.setText(array_level[randomIndex]);
        startFallingAnimation();

        return array_level[randomIndex];
    }

    protected void startFallingAnimation() {
        if (!fallingAnimation.hasStarted() || fallingAnimation.hasEnded()) {
            word.clearAnimation();
            if (checkLastStrings()) {
                fallingAnimation = createFallingAnimation(true);
            } else {
                fallingAnimation = createFallingAnimation(false);
            }
            word.startAnimation(fallingAnimation);
        }
    }


    private boolean checkLastStrings() {
        String typedText = text.toString();
        int wordLength = word.length();

        if (typedText.length() >= word.length()) {
            String text1 = typedText.substring(typedText.length() - wordLength);

            if (text1.equals(word.getText().toString())) {
                getRandomWord();
                word.clearAnimation();
                word.startAnimation(fallingAnimation);
                return true;
            }
        }
        return false;
    }


    //Animation
    private Animation createFallingAnimation(boolean startFromTop) {
        TranslateAnimation animation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, (float) (screenHeight * 0.62));

        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(speed);
        animation.setRepeatCount(Animation.INFINITE);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                getRandomWord();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

                    getRandomWord();
                    health--;
                    health_textView.setText("Health : " + health);
                    wroten_text.setText(" ");
                    if (health == 0){
                        Intent intent = new Intent(MainGameActivity.this, MainGameOver.class);
                        intent.putExtra("SCORE", score);
                        startActivity(intent);
                        finish();
                    }
            }
        });
        return animation;
    }


    private void setKeyHeightForLinearLayout(LinearLayout linearLayout, int height) {
        int childCount = linearLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = linearLayout.getChildAt(i);
            if (child instanceof TextView) {
                child.getLayoutParams().height = height;
            }
        }
    }
}
