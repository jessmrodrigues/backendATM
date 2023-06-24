package com.ifsp.atm.model;

import java.util.List;

public class AccountModel {
    private int id;
    private String name;
    private String pin;
    private String balance;
    private List<TransactionModel> transactions;
    public AccountModel(){}

    public AccountModel(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setTransactions(List<TransactionModel> transactions) {
        this.transactions = transactions;
    }

    public List<TransactionModel> getTransactions() {
        return transactions;
    }
}
