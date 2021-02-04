package com.observer.pattern.exercise;

public class MainObserver {
    public static void main(String[] args) {
        TransferObservable transferObservable = new TransferObservable("Sergio Tobón");
        TransferObserver transferObserver = new TransferObserver();

       transferObservable.addObserver(transferObserver);
       transferObservable.setName("Sergio Tobon");
    }
}
