package com.example.myfundmanager.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myfundmanager.model.DatabaseHelper;

public class DataManager {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public DataManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // 고객 추가
    public long addCustomer(String name, double investmentAmount, String investmentDate) {
        ContentValues values = new ContentValues();
        values.put("customer_name", name);
        values.put("investment_amount", investmentAmount);
        values.put("investment_date", investmentDate);

        return database.insert("Customers", null, values);
    }

    // 주식 가격 추가
    public long addStockPrice(String stockName, double price, String priceDate) {
        ContentValues values = new ContentValues();
        values.put("stock_name", stockName);
        values.put("price", price);
        values.put("price_date", priceDate);

        return database.insert("StockPrices", null, values);
    }

    // 매일 게인 조회
    public Cursor getDailyGainForStock(String stockName) {
        String query = "SELECT price_date, SUM(investment_amount * price / total_investment) AS daily_gain " +
                "FROM Customers " +
                "CROSS JOIN (SELECT SUM(investment_amount) AS total_investment FROM Customers) AS total " +
                "JOIN StockPrices ON Customers.investment_date = StockPrices.price_date " +
                "WHERE stock_name = ? " +
                "GROUP BY price_date";

        return database.rawQuery(query, new String[]{stockName});
    }
}
