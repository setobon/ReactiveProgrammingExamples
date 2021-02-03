package com.observer.pattern;

public abstract class Observer {
    public abstract void update();

    protected Subject subject;
}