package com.example.typingchallenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class registerActivity extends AppCompatActivity {

    private DatabaseReference databaseRef;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://typing-challenge-ac088-default-rtdb.firebaseio.com/");
        firebaseAuth = FirebaseAuth.getInstance();

        EditText username_editText = findViewById(R.id.username);
        EditText registration_email = findViewById(R.id.registration_email);
        EditText registration_pass = findViewById(R.id.registration_pass);
        EditText registration_passConfirm = findViewById(R.id.registration_confirmpass);
        Button registrationButton = findViewById(R.id.registration_button);
        TextView signUp = findViewById(R.id.signInText);
        TextView guestMode = findViewById(R.id.guestMode);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registerActivity.this, loginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        guestMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(registerActivity.this, "You entered the guest mode, sign up to get on the leaderboard!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(registerActivity.this, MainGameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = String.valueOf(username_editText.getText());
                String email = String.valueOf(registration_email.getText());
                password = String.valueOf(registration_pass.getText());
                String confirmPassword = String.valueOf(registration_passConfirm.getText());

                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(registerActivity.this, "Please fill all the fields!", Toast.LENGTH_LONG).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(registerActivity.this, "Passwords are not matching", Toast.LENGTH_LONG).show();
                } else {
                    databaseRef.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot snapshot) {
                            if (snapshot.hasChild(email.replace(".", ","))) {
                                Toast.makeText(registerActivity.this, "Someone already got an account with your e-mail!", Toast.LENGTH_LONG).show();
                            } else {
                                registerUser(username, email, password);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }

    private void registerUser(String username, String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(registerActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = firebaseAuth.getCurrentUser();
                            sendEmailVerification();
                        } else {
                            Toast.makeText(registerActivity.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void sendEmailVerification() {
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Please verify your e-mail!", Toast.LENGTH_LONG).show();
                    saveUserToDatabase()    ;
                    startActivity(new Intent(registerActivity.this, MainGameActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveUserToDatabase() {
        Map<String, Object> userData = new HashMap<>();
        userData.put("username", username);
        userData.put("password", password);
        userData.put("email", user.getEmail());

        databaseRef.child("users").child(user.getEmail().replace(".", ",")).setValue(userData);
    }
}
