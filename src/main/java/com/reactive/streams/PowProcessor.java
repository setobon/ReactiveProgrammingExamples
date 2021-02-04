package com.reactive.streams;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class PowProcessor extends SubmissionPublisher<Integer> implements Flow.Processor<Integer, Integer>{

    private Flow.Subscription subscription;

    //Conexión con la fuente de flujo de datos
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    //Procesa los datos que vienen de la fuente de flujo de datos (onSubscribe())
    @Override
    public void onNext(Integer item) {
        //Envía los datos al suscriptor (consumidor)
        submit(item*item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("PowProcessor complete");
        /**
         * close() -> metodo que finaliza la tarea
         */
        close();
    }
}
