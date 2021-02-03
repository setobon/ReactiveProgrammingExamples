package com.observer.pattern.exercise;

public class Account {

    private String numberAccount;
    private int balance;

    public Account(String numberAccount, int balance) {
        this.numberAccount = numberAccount;
        this.balance = balance;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "numberAccount='" + numberAccount + '\'' +
                ", balance=" + balance +
                '}';
    }

    public static String transfer(Account accountFrom, Account accountTo, int amount){

        if(validateAmount(amount, accountFrom)) {
            System.out.println("Actual balance");
            System.out.println(printAccounts(accountFrom, accountTo));

            operationOnAccounts(accountFrom, accountTo, amount);

            System.out.println("Success transaction");
            return printAccounts(accountFrom, accountTo);
        }

        return "Error: Invalid amount " + amount;
    }

    private static boolean validateAmount(int amount, Account accountFrom){
        return amount > 0 && amount <= accountFrom.getBalance();
    }

    private static void operationOnAccounts(Account accountFrom, Account accountTo, int amount) {
        accountFrom.setBalance(accountFrom.getBalance() - amount);
        accountTo.setBalance(accountTo.getBalance() + amount);

    }

    private static String printAccounts(Account accountFrom, Account accountTo) {
       return "Origin " + accountFrom.toString()
                + " Destination " + accountTo.toString();
    }


}
