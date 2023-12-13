package com.example.myfundmanager.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfundmanager.MyApplication;
import com.example.myfundmanager.R;
import com.example.myfundmanager.model.DatabaseHelper;
import com.example.myfundmanager.model.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class WithdrawActivity extends AppCompatActivity {
    DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_withdraw);

        Calendar cal = MyApplication.getFixedCalendar();

        TextView depositCurrentInvest = findViewById(R.id.withdraw_currentinvest);
        EditText depositInput = findViewById(R.id.withdraw_depositvalue);
        Button depositSubmit = findViewById(R.id.withdraw_submit);

        User currentUser = db.getUserById(getIntent().getIntExtra("userid",-1));
        double currinv = currentUser.getCurrentInvestment();

        depositInput.setText("0");
        depositCurrentInvest.setText("사용자 : "+currentUser.getUsername()+"의 현재 투자 금액 : "+currinv);


        //입금 버튼 클릭시
        depositSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
