package com.observer.pattern;

import java.util.concurrent.atomic.AtomicInteger;

public class CasesObserver extends Observer{

    public CasesObserver(Subject subject) {
        this.subject = subject;
        this.subject.registerObserver(this);
    }

    @Override
    public void update() {
        System.out.println("LowerCase: " + countLowerCase());
        System.out.println("UpperCase: " + countUpperCase());
    }

    private long countLowerCase(){
        return subject.getState().chars().filter(Character::isLowerCase).count();
    }

    private long countUpperCase(){
        return subject.getState().chars().filter(Character::isUpperCase).count();
    }
}
