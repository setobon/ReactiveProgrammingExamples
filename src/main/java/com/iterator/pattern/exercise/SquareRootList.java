package com.iterator.pattern.exercise;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

public class SquareRootList implements Iterable<Double>{

    private int position;
    private int size;
    private int number;

    public SquareRootList(int size, int number) {
        this.size = size;
        this.number = number;
    }

    @Override
    public Iterator<Double> iterator() {
        Iterator<Double> iterator = new Iterator<Double>() {
            @Override
            public boolean hasNext() {
                if(position<size){
                    return true;
                }else {
                    return false;
                }
            }

            @Override
            public Double next() {
               return Math.sqrt(number*position++);
            }
        };

        return iterator;
    }
}
