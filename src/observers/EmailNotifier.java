package observers;

import data.WeatherData;

public class EmailNotifier implements WeatherObserver {
    private final String to;

    public EmailNotifier(String to) {
        this.to = to;
    }

    @Override
    public String name() {
        return "Email->" + to;
    }

    @Override
    public void onWeather(WeatherData d) {
        System.out.printf("[Email->%s] Weather: %.1f°C, hum %.0f%%, wind %.1f m/s%n",
                to, d.getTemperatureC(), d.getHumidityPct(), d.getWindMs());
    }
}
