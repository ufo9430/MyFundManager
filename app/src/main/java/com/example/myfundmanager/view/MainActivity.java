package com.example.myfundmanager.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfundmanager.R;
import com.example.myfundmanager.model.DatabaseHelper;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    String startDate = "20231208080000";
    void setCustomdate(){
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar. YEAR, Integer.parseInt(startDate.substring(0, 4)));
        cal.set(Calendar. MONTH, Integer. parseInt(startDate.substring (4, 6)) - 1);
        cal.set(Calendar. DATE, Integer.parseInt(startDate.substring (6, 8)));
        cal.set(Calendar. HOUR, Integer.parseInt (startDate.substring (8, 10)));
        cal.set(Calendar. MINUTE, Integer.parseInt(startDate.substring(10, 12)));
        cal.set(Calendar.SECOND, Integer.parseInt (startDate.substring(12, 14)));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setCustomdate();

        Button button_login = findViewById(R.id.link_login);
        Button button_logout = findViewById(R.id.link_logout);
        Button button_register = findViewById(R.id.link_register);
        Button button_check = findViewById(R.id.link_check);
        Button button_deposit = findViewById(R.id.link_deposit);
        Button button_withdraw = findViewById(R.id.link_withdraw);

        TextView accessedUser = findViewById(R.id.accessedUser);

        if(getIntent().getStringExtra("username") == null){
            accessedUser.setText("접속 회원 : 접속중이 아닙니다.");
        }else{
            accessedUser.setText("접속 회원 : " + getIntent().getStringExtra("username"));
        }

        button_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(   MainActivity.this, LoginActivity.class);
                startActivity(myIntent);
                finish();
            }
        });


        button_logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(getIntent() != null) {
                    getIntent().getExtras().clear();
                    Toast.makeText(MainActivity.this, "로그아웃 했습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "로그인을 해주세요.", Toast.LENGTH_SHORT).show();
                }
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

        button_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}