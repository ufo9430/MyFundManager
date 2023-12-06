package com.example.myfundmanager.model;

public class User {
    private int id;
    private String username;
    private String password;
    private Stock stock; // Stock 객체를 포함하는 예시입니다.
    private int stockQuantity; // Stock 투자 개수
    private String stockInvestDate; // Stock 투자 날짜

    // 생성자, Getter 및 Setter 메서드 작성


    public void setId(int id) {
        this.id = id;
    }

    public User() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStock(Stock stock, int stockQuantity, String stockInvestDate) {
        this.stock = stock;
        this.stockQuantity = stockQuantity;
        this.stockInvestDate = stockInvestDate;
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Stock getStock() {
        return stock;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public String  getStockInvestDate() {
        return stockInvestDate;
    }
}
