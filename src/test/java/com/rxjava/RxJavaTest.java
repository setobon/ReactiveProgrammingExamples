package com.rxjava;

import io.reactivex.Observable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RxJavaTest{

    String result ="";

    @Test
    public void createSimpleObservable(){
        Observable<String> observable = Observable.just("Sergio");

        observable.subscribe((s)-> result = s);

        assertTrue(result.equals("Sergio"));
    }

    @Test
    public void showMethodsJavaRxTest(){
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        Observable<String> observable = Observable.fromArray(letters);

        observable.subscribe(

                (i) -> result+=i, //onNext()
                Throwable::printStackTrace, //onError
                ()-> result+="_Completed" //onComplete
        );

        assertTrue(result.equals("ABCDEFGHI_Completed"));
    }

    @Test
    public void showMethodJavaRxTest2(){

        String[] user = {"Sergio", "Andres", "Tobon", "Bedoya", "26", "Masculino"};
        Observable<String> observable = Observable.fromArray(user);

        observable.subscribe(
                (value) -> result += " " + value,
                Throwable::printStackTrace,
                () -> result+=" _Completed"
        );

        assertEquals(result, " Sergio Andres Tobon Bedoya 26 Masculino _Completed");
    }
}
