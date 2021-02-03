package com.observer.pattern.exercise;

public abstract class Observer {
    public abstract void update();

    protected Subject subject;
}