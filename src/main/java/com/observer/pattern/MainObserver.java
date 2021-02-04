package com.observer.pattern;

import com.observer.pattern.exercise.TransferObserver;

public class MainObserver {
    public static void main(String[] args) {
        Subject subject = new Subject("Lionel Messi");

        new LengthObserver(subject);
        new CasesObserver(subject);
        new WordsObserver(subject);

        subject.notifyObservers();

        subject.setState("Falcao Garcia alias el tigre");
        subject.setState("Juana de Arco");
        subject.setState("Kilian Mbappe");


    }
}
