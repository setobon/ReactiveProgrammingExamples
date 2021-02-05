package com.iterator.pattern.exercise;

public class Main {
    public static void main(String[] args) {

        SquareRootList squareRootList = new SquareRootList(20, 1);

        for(double i: squareRootList){
            System.out.println(i);
        }
    }
}
