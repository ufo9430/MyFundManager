package com.example.myfundmanager.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfundmanager.R;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private Button loginButton;
    private String validUsername = "your_desired_username"; // Replace with the desired username

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = usernameEditText.getText().toString().trim();

                // Check if the entered username matches the valid username
                if (enteredUsername.equals(validUsername)) {
                    // Successful login
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    // Proceed to the next activity or perform desired actions
                    // For example, start a new activity:
                    // Intent intent = new Intent(MainActivity.this, NextActivity.class);
                    // startActivity(intent);
                } else {
                    // Invalid username
                    Toast.makeText(LoginActivity.this, "Invalid username", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
