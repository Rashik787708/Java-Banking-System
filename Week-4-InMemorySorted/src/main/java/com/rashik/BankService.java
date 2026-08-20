package com.bank;

import org.mindrot.jbcrypt.BCrypt;

import java.math.BigDecimal;
import java.util.List;

public class BankService {

    private Repository<Account, Integer> repository;

    public BankService(Repository<Account, Integer> repository) {
        this.repository = repository;
    }

    public void create(int id, int pin, String name, BigDecimal balance) {

        if (repository.findById(id) != null) {
            System.out.println("Account Id already exists");
            return;
        }

        if (pin < 1000 || pin > 9999) {
            System.out.println("Invalid pin. Enter 4 digit pin");
            return;
        }

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Enter Valid name");
            return;
        }

        if (balance == null || balance.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("Initial balance cannot be negative");
            return;
        }

        String hash = BCrypt.hashpw(
                String.valueOf(pin),
                BCrypt.gensalt()
        );

        Account account = new Account(
                id,
                hash,
                name,
                balance
        );

        repository.save(account);

        System.out.println("Account Created Successfully!");
    }

    public void deposit(int id, int pin, BigDecimal amount) {

        Account account = repository.findById(id);

        if (account == null) {
            System.out.println("Account not found");
            return;
        }

        if (!BCrypt.checkpw(
                String.valueOf(pin),
                account.getPinHash())) {

            System.out.println("Incorrect Pin");
            return;
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid Deposit Amount");
            return;
        }

        account.setBalance(
                account.getBalance().add(amount)
        );

        System.out.println(
                "Current Balance : " + account.getBalance()
        );
    }

    public void withdraw(int id, int pin, BigDecimal amount) {

        Account account = repository.findById(id);

        if (account == null) {
            System.out.println("Account Not Found!");
            return;
        }

        if (!BCrypt.checkpw(
                String.valueOf(pin),
                account.getPinHash())) {

            System.out.println("Incorrect Pin");
            return;
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Enter a Valid Amount");
            return;
        }

        if (account.getBalance().compareTo(amount) < 0) {
            System.out.println("Insufficient balance");
            return;
        }

        account.setBalance(
                account.getBalance().subtract(amount)
        );

        System.out.println(
                "Current Balance : " + account.getBalance()
        );
    }

    public void checkBalance(int id, int pin) {

        Account account = repository.findById(id);

        if (account == null) {
            System.out.println("Account not Found!");
            return;
        }

        if (!BCrypt.checkpw(
                String.valueOf(pin),
                account.getPinHash())) {

            System.out.println("Incorrect Pin");
            return;
        }

        System.out.println("Account Id : " + account.getId());
        System.out.println("Account Holder Name : " + account.getName());
        System.out.println("Current Balance : " + account.getBalance());
    }

    public void viewAllAccounts() {

        List<Account> accounts = repository.findAll();

        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        for (Account account : accounts) {

            System.out.println("----------------");
            System.out.println("Account Id : " + account.getId());
            System.out.println("Account Holder Name : " + account.getName());
            System.out.println("Current Balance : " + account.getBalance());
        }
    }

    public void deleteAccount(int id) {

        Account account = repository.findById(id);

        if (account == null) {
            System.out.println("Account not found");
            return;
        }

        repository.deleteById(id);

        System.out.println("Account deleted successfully!");
    }
}