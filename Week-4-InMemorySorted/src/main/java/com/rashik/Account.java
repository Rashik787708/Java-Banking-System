package com.bank;

import java.math.BigDecimal;

public class Account {

    private int id;
    private String name;
    private String pinHash;
    private BigDecimal balance;

    public Account(int id, String pinHash, String name, BigDecimal balance) {
        this.id = id;
        this.pinHash = pinHash;
        this.name = name;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getPinHash() {
        return pinHash;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}