package data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class WeatherData {
    private final LocalDateTime timestamp;
    private final double temperatureC;
    private final double humidityPct;
    private final double pressureHPa;
    private final double windMs;

    public WeatherData(LocalDateTime timestamp, double temperatureC, double humidityPct,
                       double pressureHPa, double windMs) {
        this.timestamp = timestamp;
        this.temperatureC = temperatureC;
        this.humidityPct = humidityPct;
        this.pressureHPa = pressureHPa;
        this.windMs = windMs;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public double getTemperatureC() { return temperatureC; }
    public double getHumidityPct() { return humidityPct; }
    public double getPressureHPa() { return pressureHPa; }
    public double getWindMs() { return windMs; }

    public double getTemperatureF() {
        return temperatureC * 9 / 5 + 32;
    }

    public double getWindKmh() {
        return windMs * 3.6;
    }

    public String isoTime() {
        return timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public String toString() {
        return String.format("%s | %.1f°C | %.0f%% | %.0f hPa | %.1f m/s",
                isoTime(), temperatureC, humidityPct, pressureHPa, windMs);
    }

    public static WeatherData now(double t, double h, double p, double w) {
        return new WeatherData(LocalDateTime.now(), t, h, p, w);
    }
}
