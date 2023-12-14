package com.example.myfundmanager.view;

import static com.example.myfundmanager.model.MoneyParser.parseMoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfundmanager.MyApplication;
import com.example.myfundmanager.R;
import com.example.myfundmanager.model.DatabaseHelper;
import com.example.myfundmanager.model.MoneyParser;
import com.example.myfundmanager.model.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);
    Calendar cal = MyApplication.getFixedCalendar();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());


    public void updateFund(double fundPrice){
        Random r = new Random();
        double percent = -30+r.nextInt(60);
        double oldgain = db.getFundGainForDate(cal);
        db.updateInvestmentsByPercentage(percent);
        db.updateFundPrice(oldgain, fundPrice, Math.round(fundPrice + fundPrice * (percent/100)),1);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User currentUser = db.getUserById(getIntent().getIntExtra("userid",-1));

        Button button_login = findViewById(R.id.link_login);
        Button button_logout = findViewById(R.id.link_logout);
        Button button_register = findViewById(R.id.link_register);
        Button button_check = findViewById(R.id.link_check);
        Button button_deposit = findViewById(R.id.link_deposit);
        Button button_withdraw = findViewById(R.id.link_withdraw);
        Button button_updatecal = findViewById(R.id.updatecal);

        TextView accessedUser = findViewById(R.id.accessedUser);
        TextView userInfo = findViewById(R.id.userinfo);
        TextView dateInfo = findViewById(R.id.dateInfo);


        if(getIntent().getIntExtra("userid",-1) == -1){
            userInfo.setText("0원");
            accessedUser.setText("로그인을 해주세요.");
        }else{
            int money = (int)currentUser.getCurrentInvestment();
            userInfo.setText(parseMoney(money) +"원");
            accessedUser.setText(currentUser.getUsername()+" 님의 투자 정보");
        }


        dateInfo.setText(format.format(cal.getTime())+" 기준");

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
                if(getIntent().getIntExtra("userid",-1) != -1) {
                    Intent intent = new Intent(MainActivity.this, CheckActivity.class);
                    intent.putExtra("userid",currentUser.getId());
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "로그인을 해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getIntent().getIntExtra("userid",-1) != -1) {
                    Intent intent = new Intent(MainActivity.this, DepositActivity.class);
                    intent.putExtra("userid",currentUser.getId());
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "로그인을 해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getIntent().getIntExtra("userid",-1) != -1) {
                    Intent intent = new Intent(MainActivity.this, WithdrawActivity.class);
                    intent.putExtra("userid",currentUser.getId());
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "로그인을 해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button_updatecal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double oldfund = db.getFundPriceForDate(cal);
                cal.add(Calendar.DATE,1);
                MyApplication.setFixedCalendar(MainActivity.this,cal);

                Log.w("test","fund : "+oldfund);
                updateFund(oldfund);

                Intent intent = getIntent();
                finish();
                overridePendingTransition(0,0);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
    }
}