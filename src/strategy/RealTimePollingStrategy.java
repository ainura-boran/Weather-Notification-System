package strategy;

import data.WeatherInfo;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;


public class RealTimePollingStrategy implements UpdateStrategy {
    private WeatherInfo last;

    public RealTimePollingStrategy() {
        this.last = new WeatherInfo(LocalDateTime.now(), 15.0, 50.0, 1013.0, 3.0);
    }

    @Override
    public WeatherInfo fetch() {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        double t = clamp(last.getTemperatureC() + rnd.nextDouble(-0.8, 0.9), -35, 45);
        double h = clamp(last.getHumidityPct() + rnd.nextDouble(-4, 5), 5, 100);
        double p = clamp(last.getPressureHPa() + rnd.nextDouble(-2.5, 2.6), 970, 1045);
        double w = clamp(last.getWindMs() + rnd.nextDouble(-0.9, 1.1), 0, 30);

        last = new WeatherInfo(LocalDateTime.now(), t, h, p, w);
        return last;
    }

    @Override public String name() {
        return "Real-Time Polling";
    }

    private double clamp(double v, double min, double max) {
        return Math.max(min, Math.min(max, v));
    }
}
