package com.example.typingchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class loginActivity extends AppCompatActivity {

    Button login_button = findViewById(R.id.login_button);
    EditText login_username = findViewById(R.id.login_username);
    EditText login_email = findViewById(R.id.login_email);
    EditText login_password = findViewById(R.id.login_password);
    TextView signUpText = findViewById(R.id.signUpText);
    String usernameText = login_username.getText().toString();
    String emailText = login_email.getText().toString();
    String passwordText = login_password.getText().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, registerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}