package com.reactive.streams;

public class Sleeper{

    public static void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
