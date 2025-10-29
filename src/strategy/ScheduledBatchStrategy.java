package strategy;

import data.WeatherData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ScheduledBatchStrategy implements UpdateStrategy {
    private final int batchSize;
    private WeatherData baseline;

    public ScheduledBatchStrategy(int batchSize) {
        this.batchSize = batchSize;
        this.baseline = new WeatherData(LocalDateTime.now(), 22.0, 48.0, 1008.0, 5.0);
    }

    @Override
    public Iterable<WeatherData> fetch() {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        List<WeatherData> batch = new ArrayList<>(batchSize);
        double t = baseline.getTemperatureC(), h = baseline.getHumidityPct(),
                p = baseline.getPressureHPa(), w = baseline.getWindMs();

        for (int i = 0; i < batchSize; i++) {
            t = clamp(t + rnd.nextDouble(-1.2, 1.5), -40, 48);
            h = clamp(h + rnd.nextDouble(-6, 7), 5, 100);
            p = clamp(p + rnd.nextDouble(-3.0, 3.0), 965, 1050);
            w = clamp(w + rnd.nextDouble(-1.5, 1.8), 0, 35);
            batch.add(new WeatherData(LocalDateTime.now(), t, h, p, w));
        }

        baseline = batch.get(batch.size() - 1);
        return batch;
    }

    @Override
    public String name() {
        return "Scheduled Batch (" + batchSize + ")";
    }

    private double clamp(double v, double lo, double hi) {
        return Math.max(lo, Math.min(hi, v));
    }
}