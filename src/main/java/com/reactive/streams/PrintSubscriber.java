package com.reactive.streams;

import java.util.concurrent.Flow;

public class PrintSubscriber implements Flow.Subscriber<Integer>{

    private Flow.Subscription subscription;

    //Conexión con el intermediario
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    //Se recibe la data que retorna el intermediario, y se ejecuta la tarea (de forma sincrónica).
    @Override
    public void onNext(Integer item) {
        System.out.println("Received item" + item);
        subscription.request(1);
        Sleeper.sleep(1000);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Print subscriber complete");
    }
}
