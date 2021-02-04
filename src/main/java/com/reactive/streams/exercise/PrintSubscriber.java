package com.reactive.streams.exercise;

import com.reactive.streams.Sleeper;

import java.util.concurrent.Flow;

public class PrintSubscriber implements Flow.Subscriber<String>{

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(String item) {
        System.out.println("message received: " + item);
        subscription.request(1);
        Sleeper.sleep(1000);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Process completed");
    }
}
