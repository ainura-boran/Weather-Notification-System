package observers;

import data.WeatherInfo;

public class MobileApp implements WeatherObserver {
    private final String user;

    public MobileApp(String user) {
        this.user = user;
    }

    public String name() {
        return "Mobile:" + user;
    }

    @Override
    public void onWeather(WeatherInfo d) {
        System.out.printf("Mobile: %s | %.1f°C (%.1f°F), hum: %.0f%%, wind: %.1f km/h%n",
                user, d.getTemperatureC(), d.getTemperatureF(),
                d.getHumidityPct(), d.getWindKmh());
    }
}
