package com.rxjava;

import io.reactivex.Observable;
import org.junit.jupiter.api.Test;

public class RxJavaTest{

    @Test
    public void createSimpleObservable(){

        String result = "";

        Observable<String> observable = Observable.just("Sergio");
        //observable.subscribe((s)-> result = s);
    }
}
