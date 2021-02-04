package com.reactive.streams.exercise;

import com.reactive.streams.Sleeper;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class Main{
    public static void main(String[] args) {
        SubmissionPublisher<String> submissionPublisher = new SubmissionPublisher<>();
        Flow.Processor<String, String> powProcessor = new PowProcessor();
        Flow.Subscriber<String> printSubscriber = new PrintSubscriber();

        submissionPublisher.subscribe(powProcessor);
        powProcessor.subscribe(printSubscriber);

        List<String> messages = List.of("Este es el primer mensaje de prueba", "Llegamos al segundo", "El tercero finaliza la lista");

        messages.forEach(message -> {
            submissionPublisher.submit(message);
            Sleeper.sleep(2000);
        });

        submissionPublisher.close();
    }
}
