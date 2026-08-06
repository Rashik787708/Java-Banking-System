package com.bank;
import org.mindrot.jbcrypt.BCrypt;

import java.math.BigDecimal;
import java.util.HashMap;

public class BankService {
    private HashMap<Integer,Account> accounts= new HashMap<>();
    public Account findAccounts(int id){
        Account account = accounts.get(id);
        return account;

    }
    public void create(int id, int  pin, String name, BigDecimal balance){
        if(accounts.containsKey(id)){
            System.out.println("Account Id already exists");
            return;
        }
        if(pin>9999 || pin <1000){
            System.out.println("Invalid pin Enter 4 digit pin");
            return;
        }
        if(name.trim().isEmpty()){
            System.out.println("Enter Valid name");
            return;
        }
        if(balance.compareTo(BigDecimal.ZERO)<0){
            System.out.println("Initial balance cannot be negative");
            return;
        }
        String hash = BCrypt.hashpw(String.valueOf(pin), BCrypt.gensalt());
        Account account = new Account(id,hash,name,balance);
        accounts.put(id,account);
        System.out.println("Account Created Succesfully!");
    }
    public void deposit(int id,int pin,BigDecimal amount) {
        Account account = findAccounts(id);
        if (account == null) {
            System.out.println("Account not found");
            return;
        }
        if (!BCrypt.checkpw(String.valueOf(pin), account.getPinHash())) {
            System.out.println("Incorrect Pin");
            return;
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid Deposit Amount");
            return;
        }
        account.setBalance(account.getBalance().add(amount));
        System.out.println("Current Balance :"+account.getBalance());
    }
    public void withdraw(int id,int pin,BigDecimal amount){
        Account account = findAccounts(id);
        if(account==null){
            System.out.println("Account Not Found!");
            return;
        }
        if (!BCrypt.checkpw(String.valueOf(pin), account.getPinHash())) {
            System.out.println("Incorrect Pin");
            return;
        }
        if(amount.compareTo(BigDecimal.ZERO)<=0){
            System.out.println("Enter a Valid Amount");
            return;
        }
        if(account.getBalance().compareTo(amount)<0){
            System.out.println("Insufficient balance");
            return;
        }
        account.setBalance(account.getBalance().subtract(amount));
        System.out.println("Current Balance :"+account.getBalance());
    }
    public void checkBalance(int id,int pin){
        Account account = findAccounts(id);
        if(account==null){
            System.out.println("Account not Found!");
            return;
        }
        if (!BCrypt.checkpw(String.valueOf(pin), account.getPinHash())) {
            System.out.println("Incorrect Pin");
            return;
        }
        System.out.println("Account Id:"+account.getId());
        System.out.println("Account Holder Name:"+account.getName());
        System.out.println("Current Balance:"+account.getBalance());
    }
}
