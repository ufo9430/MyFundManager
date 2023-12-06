package com.example.myfundmanager.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Debug;
import android.util.Log;

import com.example.myfundmanager.model.Stock;
import com.example.myfundmanager.model.User;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StockApp.db";
    private static final int DATABASE_VERSION = 1;

    // User 테이블 생성 쿼리
    private static final String SQL_CREATE_USER_TABLE = "CREATE TABLE Users (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username TEXT," +
            "password TEXT," +
            "stock_id INTEGER," +
            "stock_quantity INTEGER," +
            "stock_invest_date TEXT" +
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
        db.execSQL(SQL_CREATE_STOCK_TABLE);
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

    // ----------------STOCK------------------

    // Stock 추가
    public long addStock(Stock stock) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("stock_name", stock.getStockName());
        values.put("current_price", stock.getCurrentPrice());
        long result = db.insert("Stocks", null, values);
        db.close();
        return result;
    }

    // User 수정
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("stock_id", user.getStock().getId());
        values.put("stock_quantity", user.getStockQuantity());
        values.put("stock_invest_date", user.getStockInvestDate());
        int result = db.update("Users", values, "id=?", new String[]{String.valueOf(user.getId())});
        db.close();
        return result;
    }

    // Stock 수정
    public int updateStock(Stock stock) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("stock_name", stock.getStockName());
        values.put("current_price", stock.getCurrentPrice());
        int result = db.update("Stocks", values, "id=?", new String[]{String.valueOf(stock.getId())});
        db.close();
        return result;
    }

    // 모든 User 조회
    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM Users", null);
    }

    // 모든 Stock 조회
    public Cursor getAllStocks() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM Stocks", null);
    }
}