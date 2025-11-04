package data;

import strategy.UpdateStrategy;

import java.util.ArrayList;
import java.util.List;
import observers.WeatherObserver;

public class WeatherStation {
    private final List<WeatherObserver> observers = new ArrayList<>();
    private UpdateStrategy strategy;

    public WeatherStation(UpdateStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(UpdateStrategy newStrategy) {
        System.out.println("------------------------------------------------------------");
        System.out.println("Strategy switched to: " + newStrategy.name());
        this.strategy = newStrategy;
    }

    public String currentStrategyName() {
        return strategy.name();
    }

    public void attach(WeatherObserver o) {
        observers.add(o);
    }

    public void update() {
        WeatherInfo data = strategy.fetch();
        System.out.println("------------------------------------------------------------");
        System.out.println();
        System.out.println("[WEATHER] " + data);
        for (WeatherObserver o : observers) {
            o.onWeather(data);
        }
        System.out.println("Delivered to " + observers.size() + " observers.\n");
    }
}
