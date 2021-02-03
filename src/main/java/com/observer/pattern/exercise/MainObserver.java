package com.observer.pattern.exercise;

import com.observer.pattern.exercise.Subject;

public class MainObserver {
    public static void main(String[] args) {
        Subject subject = new Subject("Sergio Tobón");

        new TransferObserver(subject);

        subject.notifyObservers();
    }
}
