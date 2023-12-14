package com.example.myfundmanager.view;

import static com.example.myfundmanager.model.MoneyParser.parseMoney;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfundmanager.MyApplication;
import com.example.myfundmanager.R;
import com.example.myfundmanager.model.DatabaseHelper;
import com.example.myfundmanager.model.User;

import java.util.Calendar;

public class CheckActivity extends AppCompatActivity {

    Calendar cal = MyApplication.getFixedCalendar();
    DatabaseHelper db = new DatabaseHelper(this);
    double getGain(double currinv){
        double fundgain = db.getFundGainForDate(cal);
        double totalfund = db.getFundPriceForDate(cal);
        return Math.round(fundgain * (currinv / totalfund));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        User currentUser = databaseHelper.getUserById(getIntent().getIntExtra("userid",-1));

        TextView checkUsername = findViewById(R.id.check_username);
        TextView checkTotalFund = findViewById(R.id.check_totalfunds);
        TextView checkCurrentInvest = findViewById(R.id.check_currentinvest);
        TextView checkUserGain = findViewById(R.id.check_usergain);
        TextView checkFundGain = findViewById(R.id.check_fundgain);

        double totfund = db.getFundPriceForDate(cal);
        double currinv = currentUser.getCurrentInvestment();

        checkUsername.setText("\uD83D\uDCC8 "+currentUser.getUsername()+"님의 투자 정보");
        checkTotalFund.setText(parseMoney((int)totfund)+"원");
        checkCurrentInvest.setText(parseMoney((int)currinv)+"원");
        checkUserGain.setText(parseMoney((int)getGain(currinv))+"원");
        checkFundGain.setText(parseMoney((int)db.getFundGainForDate(cal))+"원");

    }
}
