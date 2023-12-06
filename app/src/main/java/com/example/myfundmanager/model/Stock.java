package com.example.myfundmanager.model;

public class Stock {
    private int priceId;
    private String stockName;
    private double price;
    private String priceDate;

    public Stock(int priceId, String stockName, double price, String priceDate) {
        this.priceId = priceId;
        this.stockName = stockName;
        this.price = price;
        this.priceDate = priceDate;
    }

    public int getPriceId() {
        return priceId;
    }

    public String getStockName() {
        return stockName;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceDate() {
        return priceDate;
    }

}
