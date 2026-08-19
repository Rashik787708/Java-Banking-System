package com.bank;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        InMemoryAccountDAO dao = new InMemoryAccountDAO();

        BankService bank = new BankService(dao);

        while (true) {

            System.out.println("\n----Java Banking System----");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Amount");
            System.out.println("3. Withdraw Amount");
            System.out.println("4. Check Balance");
            System.out.println("5. View All Accounts");
            System.out.println("6. Exit");

            System.out.print("Enter Your choice : ");
            int choice = s.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter Id : ");
                    int id = s.nextInt();

                    s.nextLine();

                    System.out.print("Enter Name : ");
                    String name = s.nextLine();

                    System.out.print("Enter Pin : ");
                    int pin = s.nextInt();

                    System.out.print("Enter Balance : ");
                    BigDecimal balance = s.nextBigDecimal();

                    bank.create(id, pin, name, balance);

                    break;

                case 2:

                    System.out.print("Enter Id : ");
                    id = s.nextInt();

                    System.out.print("Enter Pin : ");
                    pin = s.nextInt();

                    System.out.print("Enter Deposit Amount : ");
                    BigDecimal amount = s.nextBigDecimal();

                    bank.deposit(id, pin, amount);

                    break;

                case 3:

                    System.out.print("Enter Id : ");
                    id = s.nextInt();

                    System.out.print("Enter Pin : ");
                    pin = s.nextInt();

                    System.out.print("Enter Withdraw Amount : ");
                    amount = s.nextBigDecimal();

                    bank.withdraw(id, pin, amount);

                    break;

                case 4:

                    System.out.print("Enter Id : ");
                    id = s.nextInt();

                    System.out.print("Enter Pin : ");
                    pin = s.nextInt();

                    bank.checkBalance(id, pin);

                    break;

                case 5:

                    bank.viewAllAccounts();

                    break;

                case 6:

                    System.out.println("Thank You!");
                    s.close();
                    return;

                default:

                    System.out.println("Invalid Choice");
            }
        }
    }
}