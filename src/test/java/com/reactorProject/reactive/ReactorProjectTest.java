package com.reactorProject.reactive;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ReactorProjectTest {

    @Test
    public void simpleStreamProjectReactorFluxTest(){
        List<Integer> elements = new ArrayList<>();

        Flux.just(1,4,3,2,5,6)
                .log()
                .subscribe(elements::add);

        assertThat(elements).contains(1,4,3,2,5,6);
    }


    @Test
    public void simpleStreamProjectReactorMonoTest(){
        List<Integer> elements = new ArrayList<>();

        Mono.just(1)
                .log()
                .subscribe(elements::add);
        assertThat(elements).contains(1);
    }

    @Test
    public void showInternalFunctionsTest(){
        List<Integer> elements = new ArrayList<>();

        Flux.just(1,4,3,2,5,6)
                .log()
                .subscribe(new Subscriber<Integer>() {
                    private Subscription s;
                    int onNextAmount;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.s = s;
                        s.request(2);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        elements.add(integer);
                        onNextAmount++;
                        if(onNextAmount%2 ==0){
                            s.request(2);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        assertThat(elements).contains(1,4,3,2,5,6);
    }

    @Test
    public void mapTest(){
        List<Integer> elements = new ArrayList<>();

        Flux.just(1,4,3,2,5,6)
                .map(element -> element *3)
                .log()
                .subscribe(elements::add);

        assertThat(elements).containsExactly(3,12,9,6,15,18);
    }

    @Test
    public void parallelTest(){
        List<Integer> elements = new ArrayList<>();

        Flux.just(1,4,3,2,5,6)
                .map(element -> element *3)
                .subscribeOn(Schedulers.parallel())
                .log()
                .subscribe(elements::add);

        assertThat(elements).containsExactly(3,12,9,6,15,18);
    }

    @Test
    public void hotStreamsTest(){
        ConnectableFlux<Object> publish = Flux.create(flux -> {
            int count = 0;
           while (count <=20){
               flux.next(System.currentTimeMillis());
               count ++;
           }
        }).sample(Duration.ofSeconds(2)).publish();

        publish.subscribe(System.out::println);
        publish.subscribe(System.out::println);
        publish.connect();
        publish.log();

    }
}
