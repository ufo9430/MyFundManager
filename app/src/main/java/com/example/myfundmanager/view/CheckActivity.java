package com.example.myfundmanager.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfundmanager.R;
import com.example.myfundmanager.model.DatabaseHelper;
import com.example.myfundmanager.model.User;

public class CheckActivity extends AppCompatActivity {

    double getGain(double initinv, double currinv){
        return initinv-currinv;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        User currentUser = databaseHelper.getUserById(getIntent().getIntExtra("userid",-1));

        TextView checkUsername = findViewById(R.id.check_username);
        TextView checkUserGain = findViewById(R.id.check_usergain);
        TextView checkInitialInvest = findViewById(R.id.check_initialinvest);
        TextView checkCurrentInvest = findViewById(R.id.check_currentinvest);

        double initinv = currentUser.getInitialInvestment();
        double currinv = currentUser.getCurrentInvestment();

        checkUsername.setText("사용자 : "+currentUser.getUsername()+"의 투자 정보");
        checkInitialInvest.setText("초기 투자 금액 : "+initinv);
        checkCurrentInvest.setText("현재 투자 금액 : "+currinv);
        checkUserGain.setText("고객 수익 : "+ getGain(initinv,currinv));

    }
}
