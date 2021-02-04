package com.reactive.streams.exercise;

public class Sleeper {

    public static void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
