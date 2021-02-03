package com.observer.pattern;

import java.util.Arrays;

public class WordsObserver extends Observer{

    public WordsObserver(Subject subject) {
        this.subject = subject;
        this.subject.registerObserver(this);
    }

    @Override
    public void update() {
        System.out.println("CountWords: " + countWords());

    }

    public long countWords(){

        String witheSpace = " ";
        return Arrays
                .stream(subject.getState().split(witheSpace)).filter(word -> !word.isEmpty()).count();
    }
}
