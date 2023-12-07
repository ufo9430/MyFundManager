package com.example.myfundmanager;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Calendar;

public class MyApplication extends Application {
    private static Calendar fixedCalendar; // 고정된 날짜를 저장할 변수
    private static final String KEY_FIXED_DATE = "fixed_date";

    @Override
    public void onCreate() {
        super.onCreate();
        // 앱 시작 시 저장된 값을 가져오거나 기본값으로 2000년 1월 1일로 설정
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        long savedDateMillis = prefs.getLong(KEY_FIXED_DATE, -1);
        fixedCalendar = Calendar.getInstance();
        if (savedDateMillis != -1) {
            fixedCalendar.setTimeInMillis(savedDateMillis);
        } else {
            fixedCalendar.set(2000, Calendar.JANUARY, 1);
        }
    }

    public static Calendar getFixedCalendar() {
        return fixedCalendar;
    }

    public void setFixedCalendar(Calendar calendar) {
        fixedCalendar = calendar;
        // 수정된 날짜를 SharedPreferences에 저장
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MyApplication.this).edit();
        editor.putLong(KEY_FIXED_DATE, calendar.getTimeInMillis());
        editor.apply();
    }
}
