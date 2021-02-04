package com.observer.pattern.weather;

import java.util.Observable;
import java.util.Observer;

public class WeatherObserver implements Observer{

    private WeatherObservable weatherUpdate;

    @Override
    public void update(Observable o, Object arg) {
        weatherUpdate = (WeatherObservable) o;
        System.out.println("Weather live: " + weatherUpdate.getWeather());
    }

}
