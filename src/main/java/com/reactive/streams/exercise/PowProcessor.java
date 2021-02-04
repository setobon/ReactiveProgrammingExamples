package com.reactive.streams.exercise;

import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class PowProcessor  extends SubmissionPublisher<String> implements Flow.Processor<String, String> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(String item) {
        long numberWords = Arrays.stream(item.split(" ")).filter(word -> !word.isEmpty()).count();
        long countLowerCase = item.chars().filter(Character::isLowerCase).count();
        long countUpperCase = item.chars().filter(Character::isUpperCase).count();

        submit("The message contain: "
                + " \nNumber Of Words: " + numberWords
                + " \n" + countLowerCase + " Lower cases"
                + " \n" + countUpperCase + " Upper cases");

        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("PowProcessor complete");
        close();
    }
}
