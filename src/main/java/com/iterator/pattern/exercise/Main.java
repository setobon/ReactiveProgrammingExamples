package com.iterator.pattern.exercise;

public class Main {
    public static void main(String[] args) {

        SquareRootList squareRootList = new SquareRootList(25, 100);

        for(double i: squareRootList){
            System.out.println(i);
        }
    }
}
