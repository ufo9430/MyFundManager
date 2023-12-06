package com.example.myfundmanager.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

import com.example.myfundmanager.R;
import com.example.myfundmanager.model.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private final long finishtimeed = 1000;
    private long presstime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_login = findViewById(R.id.link_login);
        Button button_register = findViewById(R.id.link_register);
        Button button_check = findViewById(R.id.link_check);
        Button button_deposit = findViewById(R.id.link_deposit);
        Button button_withdraw = findViewById(R.id.link_withdraw);

        button_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(   MainActivity.this, LoginActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
    }
}