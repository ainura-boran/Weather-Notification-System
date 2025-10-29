package observers;

import data.WeatherData;

public class WallDisplay implements WeatherObserver {
    private final String location;

    public WallDisplay(String location) {
        this.location = location;
    }

    @Override
    public String name() {
        return "Wall:" + location;
    }

    @Override
    public void onWeather(WeatherData d) {
        System.out.printf("[Wall:%s] %s | %.1f°C | %.0f%% | %.0f hPa | %.1f m/s%n",
                location, d.isoTime(), d.getTemperatureC(), d.getHumidityPct(),
                d.getPressureHPa(), d.getWindMs());
    }
}