package com.example.myfundmanager.view;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.Calendar;

public class DepositActivity extends AppCompatActivity {
    DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_deposit);

        TextView depositCurrentInvest = findViewById(R.id.deposit_currentinvest);
        EditText depositInput = findViewById(R.id.deposit_depositvalue);
        Button depositSubmit = findViewById(R.id.deposit_submit);

        User currentUser = db.getUserById(getIntent().getIntExtra("userid",-1));
        double currinv = currentUser.getCurrentInvestment();

        depositInput.setText("0");
        depositCurrentInvest.setText("사용자 : "+currentUser.getUsername()+"의 현재 투자 금액 : "+currinv);


        //입금 버튼 클릭시
        depositSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Calendar today = MyApplication.getFixedCalendar();
                    int deposit = Integer.parseInt(String.valueOf(depositInput.getText()));
                    double currentPrice = db.getFundPriceForDate(today);

                    db.updateFundPrice(currentPrice + Math.round(deposit));

                    Toast.makeText(DepositActivity.this, "입금을 완료했습니다.", Toast.LENGTH_SHORT).show();

                    //새로고침
                    Intent intent = getIntent();
                    finish();
                    overridePendingTransition(0,0);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                }catch (Exception e){
                    Toast.makeText(DepositActivity.this, "입력값은 숫자로 입력해야 합니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
