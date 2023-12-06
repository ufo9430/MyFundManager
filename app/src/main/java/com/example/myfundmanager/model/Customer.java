package com.example.myfundmanager.model;

public class Customer {
    private final String name;
    private Integer investment_id;
    private int investment_amount;
    public Customer(String name) {
        this.name = name;
    }

    public Integer getInvestment_id() {
        return investment_id;
    }

    public void setInvestment_id(Integer investment_id) {
        this.investment_id = investment_id;
    }

    public int getInvestment_amount() {
        return investment_amount;
    }

    public void setInvestment_amount(int investment_amount) {
        this.investment_amount = investment_amount;
    }
}
