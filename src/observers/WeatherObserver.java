package observers;

import data.WeatherData;

public interface WeatherObserver {
    String name();
    void onWeather(WeatherData data);
}
