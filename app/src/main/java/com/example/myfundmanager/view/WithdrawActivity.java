package com.example.myfundmanager.view;

import static com.example.myfundmanager.model.MoneyParser.parseMoney;

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
        depositCurrentInvest.setText("현재 투자 금액 : "+parseMoney((int)currinv)+"원");


        //입금 버튼 클릭시
        depositSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    int withdraw = Integer.parseInt(String.valueOf(depositInput.getText()));
                    double currentPrice = db.getFundPriceForDate(cal);

                    if(withdraw > currinv){
                        Toast.makeText(WithdrawActivity.this, "출금액은 투자액보다 적어야 합니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    String todaystr = dateFormat.format(cal.getTime());

                    double oldgain = db.getFundGainForDate(cal);
                    db.updateFundPrice(oldgain, currentPrice, currentPrice - Math.round(withdraw),0);

                    double usermoney = currentUser.getCurrentInvestment();
                    currentUser.newInvestment(usermoney - Math.round(withdraw),
                            todaystr);
                    db.updateUser(currentUser);


                    Toast.makeText(WithdrawActivity.this, "출금을 완료했습니다.", Toast.LENGTH_SHORT).show();
                    //새로고침
                    Intent intent = new Intent(WithdrawActivity.this, MainActivity.class);
                    intent.putExtra("userid",currentUser.getId());
                    startActivity(intent);


                }catch(Exception e){
                    Toast.makeText(WithdrawActivity.this, "입력값은 숫자로 입력해 주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
