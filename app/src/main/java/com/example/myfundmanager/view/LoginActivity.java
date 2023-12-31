package com.example.myfundmanager.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfundmanager.R;
import com.example.myfundmanager.model.DatabaseHelper;
import com.example.myfundmanager.model.User;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.loginUsernameText);
        passwordEditText = findViewById(R.id.loginPasswordText);
        loginButton = findViewById(R.id.loginButton);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = usernameEditText.getText().toString().trim();
                String enteredPassword = passwordEditText.getText().toString().trim();

                // Check if the entered username matches the valid username
                if (databaseHelper.checkUserPassword(enteredUsername,enteredPassword)) {
                    // Successful login
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    User currentUser = databaseHelper.getUserByUsername(enteredUsername);
                    intent.putExtra("userid",currentUser.getId());
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
