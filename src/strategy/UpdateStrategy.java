package strategy;
import data.WeatherData;

public interface UpdateStrategy {
    Iterable<WeatherData> fetch();
    String name();
}