package com.example.myfundmanager.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfundmanager.MyApplication;
import com.example.myfundmanager.R;
import com.example.myfundmanager.model.DatabaseHelper;
import com.example.myfundmanager.model.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    SimpleDateFormat format = new SimpleDateFormat();
    void init(){
        format.applyPattern("yyyy-MM-dd");
        databaseHelper.updateFundPrice(0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        User currentUser = databaseHelper.getUserById(getIntent().getIntExtra("userid",-1));

        Calendar cal = MyApplication.getFixedCalendar();

        Button button_login = findViewById(R.id.link_login);
        Button button_logout = findViewById(R.id.link_logout);
        Button button_register = findViewById(R.id.link_register);
        Button button_check = findViewById(R.id.link_check);
        Button button_deposit = findViewById(R.id.link_deposit);
        Button button_withdraw = findViewById(R.id.link_withdraw);

        TextView accessedUser = findViewById(R.id.accessedUser);
        TextView fundInfo = findViewById(R.id.fundInfo);
        TextView dateInfo = findViewById(R.id.dateInfo);


        if(getIntent().getIntExtra("userid",-1) == -1){
            accessedUser.setText("접속 회원 : 접속중이 아닙니다.");
        }else{
            accessedUser.setText("접속 회원 : " + currentUser.getUsername());
        }

        fundInfo.setText("총 펀드 투자액 : "+databaseHelper.getFundPriceForDate(format.format(cal.getInstance().getTime())));

        dateInfo.setText("현재 날짜 : "+format.format(cal.getTime()));

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
                if(getIntent().getIntExtra("userid",-1) != -1) {
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