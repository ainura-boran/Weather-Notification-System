package strategy;

import data.WeatherInfo;

import java.util.function.Supplier;

public class ManualInputStrategy implements UpdateStrategy {
    private final Supplier<WeatherInfo> supplier;
    private WeatherInfo last;

    public ManualInputStrategy(Supplier<WeatherInfo> supplier) {
        this.supplier = supplier;
    }

    @Override
    public WeatherInfo fetch() {
        WeatherInfo d = supplier.get();
        if (d != null) last = d;
        return (d != null) ? d : last;
    }

    @Override
    public String name() {
        return "Manual Input";
    }
}