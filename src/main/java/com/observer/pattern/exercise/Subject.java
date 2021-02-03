package com.observer.pattern.exercise;



import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Subject {
    List<Observer> observers;
    private String name;

    public Subject(String name) {
        this.observers = new LinkedList();
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyObservers();
    }

    public void registerObserver(Observer... observers){
        this.observers.addAll(Arrays.asList(observers));
    }

    public void notifyObservers(){
        for(Observer observer: observers){
            observer.update();
        }
    }

    public void remove(Observer observer){
        observers.remove(observer);
    }
}
