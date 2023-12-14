package com.example.myfundmanager.model;

public class MoneyParser {
    public static String parseMoney(int money){
        String amount = Integer.toString(money);
        return amount.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
    }
}
