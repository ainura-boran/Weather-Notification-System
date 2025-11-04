package observers;

import data.WeatherInfo;

public class DisplayBoard implements WeatherObserver {
    private final String id;

    public DisplayBoard(String id) {
        this.id = id;
    }

    @Override
    public void onWeather(WeatherInfo info) {
        System.out.println(id + " (DisplayBoard) — screen refreshed");
    }
}

