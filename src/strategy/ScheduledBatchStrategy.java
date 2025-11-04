package strategy;

import data.WeatherInfo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Iterator;

public class ScheduledBatchStrategy implements UpdateStrategy {
    private final Iterator<WeatherInfo> it;
    private WeatherInfo last;

    public ScheduledBatchStrategy(List<WeatherInfo> batch) {
        if (batch == null || batch.isEmpty()) {
            this.it = List.<WeatherInfo>of().iterator();
            this.last = new WeatherInfo(LocalDateTime.now(), 22.0, 48.0, 1008.0, 5.0);
        } else {
            this.it = batch.iterator();
            this.last = batch.get(batch.size() - 1);
        }
    }

    @Override
    public WeatherInfo fetch() {
        if (it.hasNext()) last = it.next();
        return last;
    }

    @Override
    public String name() {
        return "Scheduled Batch (preloaded)";
    }
}