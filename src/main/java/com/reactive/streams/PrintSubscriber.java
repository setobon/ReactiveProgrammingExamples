package com.reactive.streams;

import java.util.concurrent.Flow;

public class PrintSubscriber implements Flow.Subscriber<Integer>{

    /**
     * subscription -> suscripción a algún escuchador, observador o algo que está esperando para mostrar, puede llamarse
     * observer.
     */
    private Flow.Subscription subscription;

    /**
     *
     * onSubscribe() -> metodo que realiza la suscripción
     */
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        /**
         * request -> solicitud de la suscripción
         */
        subscription.request(1);
    }


    /**
     *
     * onNext() método que ejecuta la tarea suscrita
     */
    @Override
    public void onNext(Integer item) {
        System.out.println("Received item" + item);
        subscription.request(1);
        Sleeper.sleep(1000);
    }

    /**
     *
     * onError() -> manera de controlar los errores, en caso de fallar la tarea suscrita
     */
    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * onComplete() -> método que finaliza la tarea suscrita, en caso de ser exitoso.
     */
    @Override
    public void onComplete() {
        System.out.println("Print subscriber complete");
    }
}
