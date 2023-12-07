package com.example.myfundmanager.model;

public class User {
    private int id;
    private String username;
    private String password;
    private double initialInvestment;
    private double currentInvestment;
    private String InvestDate; // Stock 투자 날짜

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

    public void newInvestment(double initialInvestment, String InvestDate) {
        this.initialInvestment = initialInvestment;
        this.InvestDate = InvestDate;
    }

    public void updateInvestment(double updatedCost, String InvestDate){
        this.currentInvestment = updatedCost;
        this.InvestDate = InvestDate;
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

    public double getCurrentInvestment() {return currentInvestment;}

    public String getInvestDate() {
        return InvestDate;
    }
}
