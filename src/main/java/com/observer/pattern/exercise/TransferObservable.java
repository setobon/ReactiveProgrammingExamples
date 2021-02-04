package com.observer.pattern.exercise;

import java.util.Observable;

public class TransferObservable extends Observable {

    private String name;

    public TransferObservable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers();
    }
}
