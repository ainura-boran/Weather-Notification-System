package strategy;

import data.WeatherData;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ManualInputStrategy implements UpdateStrategy {
    private final Queue<WeatherData> queue = new ConcurrentLinkedQueue<>();

    public void offer(WeatherData data) {
        if (data != null)
            queue.offer(data);
    }

    @Override
    public Iterable<WeatherData> fetch() {
        List<WeatherData> drained = new ArrayList<>();
        WeatherData d;
        while ((d = queue.poll()) != null)
            drained.add(d);
        return drained;
    }

    @Override
    public String name() {
        return "Manual Input";
    }
}