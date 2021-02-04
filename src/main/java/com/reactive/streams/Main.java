package com.reactive.streams;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.IntStream;

public class Main{
    public static void main(String[] args) {
        //SubmissionPublisher -> Es un productor o fuente de flujo de datos
        SubmissionPublisher<Integer> submissionPublisher = new SubmissionPublisher<>();

        //Procesor -> intermediario, que hace transformacion de los flujos de datos
        Flow.Processor<Integer, Integer> powProcessor = new PowProcessor();

        //printSubscriber -> consumidor de la fuente de flujos de datos
        Flow.Subscriber<Integer> printSubscriber = new PrintSubscriber();

        //suscribe() -> se suscribe a la fuente de flujo de datos
        submissionPublisher.subscribe(powProcessor);

        //powProcessor -> luego de procesar los datos, se convierte también en una fuente de flujo de datos
        //para poder proporcionar al consumidor
        powProcessor.subscribe(printSubscriber);

        IntStream.range(0, 20)
                .forEach(i -> {
                    submissionPublisher.submit(i);
                    Sleeper.sleep(2000);
                });

        //close() -> se llama al oncomplete de submissionPublisher y termina todo el flujo
        submissionPublisher.close();
     }
}
