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
        initialInvestment = 0;
        currentInvestment = 0;
        InvestDate = "현재 입금을 하지 않았습니다.";
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 입금,출금 시 호출
    public void newInvestment(double Investment, String InvestDate) {
        this.initialInvestment = Investment;
        this.currentInvestment = Investment;
        this.InvestDate = InvestDate;
    }

    // 펀드 수익 변화 시 호출
    public void updateInvestment(double updatedCost, String InvestDate){
        this.currentInvestment = updatedCost;
        this.InvestDate = InvestDate;
    }

    public double getInitialInvestment() {
        return initialInvestment;
    }

    public void setInitialInvestment(double initialInvestment) {
        this.initialInvestment = initialInvestment;
    }

    public void setCurrentInvestment(double currentInvestment) {
        this.currentInvestment = currentInvestment;
    }

    public void setInvestDate(String investDate) {
        InvestDate = investDate;
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
