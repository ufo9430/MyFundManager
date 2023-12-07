package com.example.myfundmanager.model;

public class Stock {
    private int id;
    private double currentPrice; // Fund의 현재 가격

    private double lastDayPrice;

    // 생성자, Getter 및 Setter 메서드 작성
    public int getId() {
        return id;
    }

    public void setCurrentPrice(int modifiedPrice) {
        this.currentPrice = modifiedPrice;
    }
    public double getCurrentPrice() {
        return currentPrice;
    }
}