package com.rxjava;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class RxJavaTest{

    String result ="";
    int resultSum = 0;

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

    @Test
    public void mapOperatorTest(){
        String[] letters = {"a","b","c","d","e","f","g","h","i","j","k",};

        Observable.fromArray(letters)
                .map(String::toUpperCase)
                .subscribe(
                        letter -> result+=letter,
                        Throwable::printStackTrace,
                        ()->result+="_Completed");
        assertTrue(result.equals("ABCDEFGHIJK_Completed"));
    }


    @Test
    public void mapOperatorV2Test(){
        Observable.fromIterable(getCars())
                .map(carBrand -> carBrand.split(" ")[0].toUpperCase())
                .subscribe(car -> result+=car + " - ");

        assertTrue(result.equals("VOLKSWAGEN - RENAULT - SUBARU - TOYOTA - TOYOTA - TOYOTA - "));
    }


    @Test
    public void scanOperatorTest(){
        String[] letters = {"a","b","c"};

        Observable.fromArray(letters)
                .scan(new StringBuilder(), StringBuilder::append)
                .subscribe(total -> result+=total.toString());

        assertTrue(result.equals("aababc"));
    }

    @Test
    public void groupByOperatorTest(){
        Integer[] numbers = {1,2,3,4,5,6,7,8,9,10};
        String[] odd = {""};
        String[] even = {""};

        Observable.fromArray(numbers)
                .groupBy(i -> i%2==0?"even":"odd")
                .subscribe(group ->
                        group.subscribe(number -> {
                            if(group.getKey().toString().equals("even")){
                                even[0]+=number;
                            }else{
                                odd[0]+=number;
                            }
                        }));

        assertTrue(even[0].equals("246810"));
        assertTrue(odd[0].equals("13579"));
    }

    @Test
    public void groupByOperatorV2Test(){
        String[] carBrand = {""};
        String[] carReference = {""};

        Observable.fromIterable(getCars())
                .groupBy(car ->{
                    if(!(car.split(" ")[0]).isEmpty()) {
                        return "brand";
                    }
                    if(!(car.split(" ")[1]).isEmpty()){
                        return "reference";
                    }
                    return null;
                })
                .subscribe(group ->
                        group.subscribe(car -> {
                            if(group.getKey().equals("brand")){
                                carBrand[0]+=car.split(" ")[0] + " ";
                            }else{
                                carReference[0]+=car.split(" ")[1];
                            }
                        }));

        System.out.println(carBrand[0]);
        System.out.println(carReference[0]);
    }

    @Test
    public void filterOperatorTest(){
        Integer[] numbers = {0,1,2,3,4,5,6,7,8,9,10};


        Observable.fromArray(numbers)
                .filter(number -> number%2==1)
                .subscribe(number -> result+=number);

        assertTrue(result.equals("13579"));
    }

    @Test
    public void filterOperatorV2Test() {
        Integer[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Observable.fromIterable(getCars())
                .filter(car -> car.split(" ")[0].equals("Toyota"))
                .subscribe(car -> result += car + " - ");

        assertTrue(result.equals("Toyota TXL - Toyota corolla - Toyota RAV4 - "));
    }

    @Test
    public void defaultIfEmptyWithObservableEmptyTest(){

        Observable.empty()
                .defaultIfEmpty("Error: this is empty observable")
                .subscribe(i -> result+=i);

        assertTrue(result.equals("Error: this is empty observable"));
    }

    @Test
    public void defaultIfEmptyWithDataTest(){
        String[] letters = {"a","b","c","d","e","f","g","h","i","j","k"};
        Observable.fromArray(letters)
                .defaultIfEmpty("Error: this is empty observable")
                .firstElement()
                .subscribe(i -> result+=i);

        assertTrue(result.equals("a"));
    }

    @Test
    public void defaultIfEmptyWithDataV2Test(){

        Observable.fromIterable(getCars())
                .defaultIfEmpty("Error: this is empty observable")
                .firstElement()
                .subscribe(i -> result+=i);

        assertTrue(result.equals("Volkswagen Jetta"));
    }

    @Test
    public void takeWhileTest(){
        Integer[] numbers = {0,1,2,3,4,5,6,7,8,9,10};

        Observable.fromArray(numbers)
                .takeWhile(number -> number<5)
                .subscribe(i -> resultSum += i);

        assertEquals(10, resultSum);
    }

    @Test
    public void takeWhileV2Test(){
        Observable.fromIterable(getCars())
                .takeWhile(car -> !car.split(" ")[0].isEmpty())
                .subscribe(car -> result += car  + " - ");

        assertEquals("Volkswagen Jetta - Renault Duster - Subaru XV - Toyota TXL - Toyota corolla - Toyota RAV4 - ", result);
    }

    @Test
    public void showObservableCold(){
        Observable<String> observable = Observable.just("a", "b", "c", "d");

        observable.subscribe((observerOne)-> System.out.println("observer one: " + observerOne));
        observable.subscribe((observerTwo)-> System.out.println("observer two: " + observerTwo));
    }


    public void showObservableHot() {
        ConnectableObservable<String> observable = Observable.just("a", "b", "c", "d").publish();

        observable.subscribe((observerOne) -> System.out.println("observer one: " + observerOne));
        observable.subscribe((observerTwo) -> System.out.println("observer two: " + observerTwo));

       observable.connect();

    }

    @Test
    public void showObservableHotTest() throws InterruptedException {
        String[] result = {""};
        ConnectableObservable<Long> connectable =
                Observable.interval(200, TimeUnit.MILLISECONDS).publish();
        connectable.subscribe(i -> result[0] += i);

        assertFalse(result[0].equals("01"));

        connectable.connect();
        Thread.sleep(500);

        assertTrue(result[0].equals("01"));
    }

    @Test
    public void intervalTest() throws InterruptedException {
       ConnectableObservable<Long> observable =
               Observable.interval(1, TimeUnit.SECONDS).publish();

       observable.subscribe(i -> System.out.println("Second observable one " + i));
        observable.connect();
        Thread.sleep(5000);
       observable.subscribe(i -> System.out.println("Second observable two " + i));
       Thread.sleep(5000);
    }


    @Test
    public void intervalExerciseTest() throws InterruptedException {
        ConnectableObservable<Long> observable =
                Observable.interval(1, TimeUnit.SECONDS).publish();

        observable.subscribe(i ->
                System.out.println("This is the player one and the the time of the game is " + i));

        observable.subscribe(i ->
                System.out.println("This is the player two and the the time of the game is " + i));
        observable.connect();
        Thread.sleep(10000);
    }

    private static int start = 1;
    private static int count = 5;

    @Test
    public void deferTest(){

        Observable<Integer> observable = Observable.defer(()->Observable.range(start, count));
        observable.subscribe(i -> System.out.println("Observer one: " + i));
        count = 10;
        observable.subscribe(i -> System.out.println("Observer two: " + i));
        count = 20;
        observable.subscribe(i -> System.out.println("Observer three: " + i));
    }

    @Test
    public void deferV2Test(){

        Observable<Integer> observable = Observable.defer(()->Observable.range(start, count));
        System.out.println("Se debe contar la cantidad de páginas que tiene cada documento");

        observable.subscribe(i -> System.out.println("document one: " + i));
        count = 10;
        observable.subscribe(i -> System.out.println("document two: " + i));
        count = 20;
        observable.subscribe(i -> System.out.println("document three: " + i));
    }

    @Test
    public void disposableTest(){
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);
        Disposable disposable = observable.subscribe(i -> System.out.println("Received " + i));
        sleep(5000);
        //disposable.dispose();
        sleep(5000);
    }

    @Test
    public void disposableV2Test(){
        System.out.println("Solo se acepta la compra de 10 articulos por persona -- counting:");

        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);

        Disposable disposable = observable.subscribe(i -> System.out.println("article " + (i+1)));

        sleep(10000);

        disposable.dispose();
        System.out.println("Ha excedido el limite de articulos.");

        sleep(5000);
        System.out.println("counting has finished");
    }

    @Test
    public void distinctTest(){
        Observable<String> observable = Observable.just("alpha","beta","gama","delta","epsilon");
        observable
                //.map(String::length)
                .distinct(String::length)
                .subscribe(i -> System.out.println("received " + i));
    }

    @Test
    public void distinctV2Test(){
        Observable<String> observable = Observable.fromIterable(getCars());
        observable
                //.map(String::length)
                .distinct(car -> car.split(" ")[0])
                .subscribe(car -> System.out.println("Brand: " + car.split(" ")[0]));
    }

    @Test
    public void sortTest(){
        Observable.fromIterable(getCars())
                .sorted()
                .subscribe(System.out::println);
    }

    @Test
    public void sortStringTest(){
        Observable.just("alpha","beta","gama","delta","epsilon")
                .sorted()
                .subscribe(System.out::println);
    }

    @Test
    public void sortV2Test(){
        Observable.just(1, 24,65,12,34,21,768)
                .sorted(Comparator.reverseOrder())
                .subscribe(System.out::println);
    }

    @Test
    public void sortByLengthTest(){
        Observable.just("alpha","beta","gama","delta","epsilon")
                .sorted((x, y) -> Integer.compare(x.length(), y.length()))
                .subscribe(System.out::println);
    }

    @Test
    public void sortByLengthV2Test(){
        Observable.fromIterable(getCars())
                .sorted((x, y) -> Integer.compare(x.length(), y.length()))
                .subscribe(System.out::println);
    }

    @Test
    public void contains(){
        Observable
                .range(0, 10000)
                .contains(9563).subscribe(i -> System.out.println("Received " + i));
    }

    @Test
    public void containsV2(){
        Observable
                .fromIterable(getCars())
                .contains("Toyota TXL")
                .subscribe(i -> System.out.println("Received " + i));
    }


    private List<String> getCars() {
        List<String> carList = new ArrayList<>();

        carList.add("Volkswagen Jetta");
        carList.add("Renault Duster");
        carList.add("Subaru XV");
        carList.add("Toyota TXL");
        carList.add("Toyota corolla");
        carList.add("Toyota RAV4");
        return carList;
    }

    private static void sleep(int millis){
        try {
            Thread.sleep(millis);
        }catch (InterruptedException exception){
            exception.printStackTrace();
        }
    }

}
