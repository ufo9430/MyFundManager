package com.example.myfundmanager.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Fund.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_FUND = "funds";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_PRICE = "price";

    // User 테이블 생성 쿼리
    private static final String SQL_CREATE_USER_TABLE = "CREATE TABLE Users (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username TEXT," +
            "password TEXT," +
            "stock_id INTEGER," +
            "stock_quantity INTEGER," +
            "stock_invest_date TEXT" +
            ")";

    private static final String CREATE_FUND_TABLE = "CREATE TABLE " + TABLE_FUND + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_DATE + " TEXT, " +
            COLUMN_PRICE + " REAL" +
            ")";

    // User 중복조회

    public boolean checkUsernameExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE username=?", new String[]{username});
        boolean exists = cursor.getCount() > 0;
        Log.v("test",Integer.toString(cursor.getCount()));
        return exists;
    }

    // User 로그인 조회

    @SuppressLint("Range")
    public boolean checkUserPassword(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE username=? AND password=?", new String[]{username, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Stock 테이블 생성 쿼리
    private static final String SQL_CREATE_STOCK_TABLE = "CREATE TABLE Stocks (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "stock_name TEXT," +
            "current_price REAL" +
            ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER_TABLE);
        db.execSQL(CREATE_FUND_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 데이터베이스 업그레이드 시 필요한 작업 수행
    }

    // User 추가
    public long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("stock_id", -1);
        values.put("stock_quantity", -1);
        values.put("stock_invest_date", -1);
        long result = db.insert("Users", null, values);
        db.close();
        return result;
    }

    // username을 기반으로 User 객체 반환
    @SuppressLint("Range")
    public User getUserByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE username=?", new String[]{username});
        if (cursor != null && cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex("id")));
            user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            // 추가적으로 필요한 정보 설정 가능
        }
        if (cursor != null) {
            cursor.close();
        }
        return user;
    }

    // User 수정
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("stock_quantity", user.getDeposit());
        values.put("stock_invest_date", user.getInvestDate());
        int result = db.update("Users", values, "id=?", new String[]{String.valueOf(user.getId())});
        db.close();
        return result;
    }

    // 모든 User 조회
    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM Users", null);
    }

    public void updateFundPrice(double newPrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date currentDate = Calendar.getInstance().getTime();
        String formattedDate = dateFormat.format(currentDate);

        values.put(COLUMN_DATE, formattedDate);
        values.put(COLUMN_PRICE, newPrice);

        db.insert(TABLE_FUND, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public double getFundPriceForDate(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        double fundPrice = 0.0;
        Cursor cursor = db.query(
                TABLE_FUND,
                new String[]{COLUMN_PRICE},
                COLUMN_DATE + "=?",
                new String[]{date},
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            fundPrice = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE));
            cursor.close();
        }
        db.close();

        return fundPrice;
    }
}