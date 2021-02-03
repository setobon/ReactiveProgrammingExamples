package com.observer.pattern.exercise;

public class TransferObserver extends Observer {

    public TransferObserver(Subject subject) {
        this.subject = subject;
        this.subject.registerObserver(this);
    }

    @Override
    public void update() {
        int amount = 23;
        Account accountFrom = new Account("420-502387-01", 100);
        Account accountTo = new Account("420-502387-02", 100);

        System.out.println("User: " + subject.getName());
        System.out.println(Account.transfer(accountFrom, accountTo, amount));
        System.out.println("Account updated");
    }
}
