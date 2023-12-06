package com.example.myfundmanager.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StockManagement.db";
    private static final int DATABASE_VERSION = 1;

    // Customers 테이블 생성 쿼리
    private static final String SQL_CREATE_CUSTOMERS_TABLE = "CREATE TABLE Customers (" +
            "customer_id INTEGER PRIMARY KEY," +
            "customer_name TEXT," +
            "investment_amount REAL," +
            "investment_date TEXT" +
            ")";

    // StockPrices 테이블 생성 쿼리
    private static final String SQL_CREATE_STOCK_PRICES_TABLE = "CREATE TABLE StockPrices (" +
            "price_id INTEGER PRIMARY KEY," +
            "stock_name TEXT," +
            "price REAL," +
            "price_date TEXT" +
            ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 테이블 생성
        db.execSQL(SQL_CREATE_CUSTOMERS_TABLE);
        db.execSQL(SQL_CREATE_STOCK_PRICES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 데이터베이스 업그레이드 시 필요한 작업 수행
    }
}
