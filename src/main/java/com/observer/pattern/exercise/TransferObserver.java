package com.observer.pattern.exercise;

import com.observer.pattern.weather.WeatherObservable;

import java.util.Observable;
import java.util.Observer;

public class TransferObserver implements Observer {

    private TransferObservable transferUpdate;


    @Override
    public void update(Observable o, Object arg) {
        int amount = 23;
        Account accountFrom = new Account("420-502387-01", 100);
        Account accountTo = new Account("420-502387-02", 100);

        transferUpdate = (TransferObservable) o;

        System.out.println("User: " + transferUpdate.getName() + " "
                + Account.transfer(accountFrom, accountTo, amount));
    }
}
