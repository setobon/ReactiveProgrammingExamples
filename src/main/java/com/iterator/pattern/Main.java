package com.iterator.pattern;

public class Main {
    public static void main(String[] args) {
        MultiplyList multiplyList = new MultiplyList(10, 3);

        for(int i: multiplyList){
            System.out.println(i);
        }
    }
}
