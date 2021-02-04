package com.observer.pattern.weather;

public class MainWeather {
    public static void main(String[] args) {
        WeatherObserver weatherObserver = new WeatherObserver();
        WeatherObservable weatherObservable = new WeatherObservable(null);

        weatherObservable.addObserver(weatherObserver);
        weatherObservable.setWeather("Raining");
        weatherObservable.setWeather("Sunny");
    }
}
