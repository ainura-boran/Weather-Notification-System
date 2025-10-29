import data.WeatherData;
import observers.WeatherObserver;
import strategy.UpdateStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeatherStation {
    private final List<WeatherObserver> observers = new ArrayList<>();
    private UpdateStrategy strategy;

    public WeatherStation(UpdateStrategy initialStrategy) {
        this.strategy = initialStrategy;
    }

    public void register(WeatherObserver obs) {
        if (obs != null && !observers.contains(obs)) observers.add(obs);
    }

    public void unregister(WeatherObserver obs) { observers.remove(obs); }

    public void setStrategy(UpdateStrategy newStrategy) {
        if (newStrategy != null) {
            System.out.println("\n>>> Switched to strategy: " + newStrategy.name());
            this.strategy = newStrategy;
        }
    }

    public void updateNow() {
        for (WeatherData data : strategy.fetch()) {
            for (WeatherObserver o : observers) {
                o.onWeather(data);
            }
        }
    }

    public List<WeatherObserver> getObservers() {
        return Collections.unmodifiableList(observers);
    }
}
