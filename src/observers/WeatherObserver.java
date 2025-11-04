package observers;

import data.WeatherInfo;

public interface WeatherObserver {
    void onWeather(WeatherInfo data);
}
