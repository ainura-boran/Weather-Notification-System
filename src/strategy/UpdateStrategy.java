package strategy;
import data.WeatherInfo;

public interface UpdateStrategy {
    WeatherInfo fetch();
    String name();
}
